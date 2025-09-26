package io.github.Rodfc773.rpg_references_api.users.domain.exceptions;

import java.time.Instant;

public class ErrorMessageDTO {

    private String message;
    private String field;
    private Instant timeStamp;
    private String path;
    private int status;

    ErrorMessageDTO(String message, String field){
        this.message = message;
        this.field = field;
    }

    public String getMessage(){ return this.message; }

    public String getField(){ return this.field; }

    public String getPath() { return  this.field; }

    public int getStatus() { return this.status; }

    public String getTimeStamp() { return this.timeStamp.toString(); }

    public void setMessage(String message) { this.message = message; }

    public void  setField(String field){ this.field = field; }

    public void setTimeStamp() { this.timeStamp = Instant.now(); }

    public void setPath(String path) { this.path = path; }

    public void setStatus(int status) { this.status = status; }
}
