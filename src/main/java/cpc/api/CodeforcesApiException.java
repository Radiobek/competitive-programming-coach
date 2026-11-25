package cpc.api;

public class CodeforcesApiException extends Exception {
    public CodeforcesApiException(String message) {
        super(message);
    }

    public CodeforcesApiException(String message, Throwable cause) {
        super(message, cause);
    }
}