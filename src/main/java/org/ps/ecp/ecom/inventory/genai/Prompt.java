package org.ps.ecp.ecom.inventory.genai;

public class Prompt {

    String id;
    String message;
    Options options;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

}
