package wtf.beatrice.releasehive.model;

import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class ApiError
{
    UUID exceptionId;

    String message;

    public ApiError() {
        exceptionId = UUID.randomUUID();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(UUID exceptionId) {
        this.exceptionId = exceptionId;
    }
}
