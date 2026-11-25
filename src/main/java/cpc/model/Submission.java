package cpc.model;

public class Submission {
    private long id;
    private long creationTimeSeconds;
    private String verdict;
    private Problem problem;

    public long getId() { return id; }
    public long getCreationTimeSeconds() { return creationTimeSeconds; }
    public Problem getProblem() { return problem; }
    public boolean isSolved() { return "OK".equals(verdict); }
}