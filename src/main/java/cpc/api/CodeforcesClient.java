package cpc.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

public class CodeforcesClient {
    private static final String BASE_URL = "https://codeforces.com/api/";
    private static final long MIN_DELAY_MS = 2000; // Codeforces asks for ~1 request per 2s

    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private long lastCallTime = 0;

    // synchronized so the rate limit also holds if you call it from several threads
    private synchronized JsonElement get(String method, Map<String, String> params)
            throws CodeforcesApiException {
        try {
            long wait = MIN_DELAY_MS - (System.currentTimeMillis() - lastCallTime);
            if (wait > 0) {
                Thread.sleep(wait);
            }

            String query = params.entrySet().stream()
                    .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + method + "?" + query))
                    .timeout(Duration.ofSeconds(15))
                    .GET()
                    .build();

            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            lastCallTime = System.currentTimeMillis();

            // Codeforces returns a JSON body even for errors (e.g. HTTP 400), so parse first
            JsonObject root = JsonParser.parseString(response.body()).getAsJsonObject();
            String status = root.get("status").getAsString();

            if (!"OK".equals(status)) {
                String comment = root.has("comment") ? root.get("comment").getAsString() : "Unknown error";
                throw new CodeforcesApiException(comment);
            }
            return root.get("result");

        } catch (IOException e) {
            throw new CodeforcesApiException("Network error: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CodeforcesApiException("Request interrupted", e);
        } catch (JsonParseException | IllegalStateException e) {
            throw new CodeforcesApiException("Unexpected response (not valid API JSON)", e);
        }
    }

    public JsonObject getUserInfo(String handle) throws CodeforcesApiException {
        return get("user.info", Map.of("handles", handle))
                .getAsJsonArray().get(0).getAsJsonObject();
    }

    public JsonArray getSubmissions(String handle) throws CodeforcesApiException {
        return get("user.status", Map.of("handle", handle)).getAsJsonArray();
    }

    public JsonArray getRatingHistory(String handle) throws CodeforcesApiException {
        return get("user.rating", Map.of("handle", handle)).getAsJsonArray();
    }

    public JsonObject getProblemset() throws CodeforcesApiException {
        return get("problemset.problems", Map.of()).getAsJsonObject();
    }
}