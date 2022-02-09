package exception;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FeastErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private String timestamp;
    private int status;
    private String message;
    private String cause;

    public FeastErrorResponse() {
    }

    public FeastErrorResponse(String timestamp, int status, String message, String cause) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.cause = cause;
    }

    public FeastErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
