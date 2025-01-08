package org.ps.ecp.ecom.genai;

public enum OpenAIModels {
    GPT_4O_MINI("gpt-4o-mini"),
    GPT_4O("gpt-4o"),

    DALL_E_2("dall-e-2"),

    DALL_E_3("dall-e-3");

    private String message;

    OpenAIModels(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
