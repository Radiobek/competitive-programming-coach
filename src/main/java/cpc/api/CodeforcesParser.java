package cpc.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import cpc.model.Problem;
import cpc.model.Submission;

import java.util.List;

public class CodeforcesParser {
    private static final Gson GSON = new Gson();

    public static List<Submission> parseSubmissions(JsonArray array) {
        return GSON.fromJson(array, new TypeToken<List<Submission>>() {}.getType());
    }

    public static List<Problem> parseProblems(JsonObject result) {
        return GSON.fromJson(result.get("problems"), new TypeToken<List<Problem>>() {}.getType());
    }
}