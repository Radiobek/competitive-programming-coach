package cpc;

import cpc.api.CodeforcesApiException;
import cpc.api.CodeforcesClient;
import cpc.api.CodeforcesParser;
import cpc.model.Submission;
import java.util.List;

//testing the client's code
public class Main {
    public static void main(String[] args) {
        CodeforcesClient client = new CodeforcesClient();
        try {
            List<Submission> subs = CodeforcesParser.parseSubmissions(client.getSubmissions("Radiobas"));
            long solved = subs.stream().filter(Submission::isSolved).count();
            System.out.println("Total: " + subs.size() + ", accepted: " + solved);
            System.out.println("First problem tags: " + subs.get(0).getProblem().getTags());
        } catch (CodeforcesApiException e) {
            System.err.println("API error: " + e.getMessage());
        }
    }
}