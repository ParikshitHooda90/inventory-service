package org.ps.ecp.ecom.genai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Component
public class SandBoxGptUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SandBoxGptUtils.class);
    @Autowired
    private SandBoxGptConfig sandBoxGptEntity;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;

    public String getChatCompletionMessage(String content) throws IOException {
        Message message1 = new Message();
        message1.setContent("You are a helpful assistant");
        message1.setRole("system");

        Message message2 = new Message();
        message2.setContent(content);
        message2.setRole("user");

        CompletionModelRequest request = new CompletionModelRequest();
        request.setModel(OpenAIModels.GPT_4O.getMessage());
        request.setMessages(Arrays.asList(message1,message2));

       return invokeOpenAI(getChatCompletionURL(), request);
    }

    private String getChatCompletionURL(){
        return sandBoxGptEntity.getBaseurl() + "chat/completions";
    }

    private String invokeOpenAI(String url, Object payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer sk-proj-9gZgO4kOhs6RkXgNrgvbT3BlbkFJogfmpgfxmwrBVZ7K95Df");
        try {
            HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(payload), headers);
            return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
        } catch (RestClientException | JsonProcessingException e) {
            e.printStackTrace();
            return "Error occurred while processing the request.";
        }
    }

}
