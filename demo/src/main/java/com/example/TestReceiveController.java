package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.example.TestDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TestReceiveController {

    private static final String API_URL = "http://localhost:8080/testJson/api/json/";

    @RequestMapping(value = "/api/result/", method = RequestMethod.GET)
    public String test(Model model) throws JsonParseException, JsonMappingException, IOException {
        List<TestDto> dtoList = convertTest(executeGet(API_URL));
        model.addAttribute("dtoList", dtoList);
        return "apiresult";
    }
    
    /**
     * JSONをdecodeし、Listに詰める
     * @param json
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    private List<TestDto> convertTest(String json) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<TestDto> list = mapper.readValue(json, new TypeReference<List<TestDto>>() {
        });
        return list;
    }
    
    /**
     * ControllerからAPIをコールし、JSON文字列を取得
     * @param urlStr
     * @return
     */
    private String executeGet(final String urlStr) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
                            StandardCharsets.UTF_8);
                            BufferedReader reader = new BufferedReader(isr)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    }
                }
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
