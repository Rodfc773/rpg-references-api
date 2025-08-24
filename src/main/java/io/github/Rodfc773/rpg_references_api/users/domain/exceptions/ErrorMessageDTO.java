package io.github.Rodfc773.rpg_references_api.users.domain.exceptions;

public class ErrorMessageDTO {

    private String message;
    private String field;

    ErrorMessageDTO(String message, String field){
        this.message = message;
        this.field = field;
    }

    public String getMessage(){ return this.message; }

    public String getField(){ return this.field; }

    public void setMessage(String message) { this.message = message; }

    public void  setField(String field){ this.field = field; }
}
