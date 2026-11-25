package cpc.model;

import java.util.List;

public class Problem {
    private int contestId;
    private String index;
    private String name;
    private Integer rating;     // Integer (not int) because unrated problems have no rating
    private List<String> tags;

    public String getId() { return contestId + index; }
    public String getName() { return name; }
    public Integer getRating() { return rating; }
    public List<String> getTags() { return tags; }
}