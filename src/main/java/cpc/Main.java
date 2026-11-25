package cpc;

import cpc.api.CodeforcesApiException;
import cpc.api.CodeforcesClient;

//testing the client's code
public class Main {
    public static void main(String[] args) {
        CodeforcesClient client = new CodeforcesClient();
        try {
            System.out.println("Rating: " + client.getUserInfo("tourist").get("rating"));
            System.out.println("Submissions: " + client.getSubmissions("tourist").size());
        } catch (CodeforcesApiException e) {
            System.err.println("API error: " + e.getMessage());
        }
    }
}