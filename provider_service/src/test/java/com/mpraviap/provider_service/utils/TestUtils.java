package com.mpraviap.provider_service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestUtils {
    private static final String FILE_PATH = "src/test/resources/";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readJsonFromFile(String filePath, String nameObject, Class<T> valueType) throws IOException {
        String path = FILE_PATH + filePath + "/" + nameObject + ".json";
        return objectMapper.readValue(new File(path), valueType);
    }

    public static String toJson(Object value) throws IOException {
        return objectMapper.writeValueAsString(value);
    }
}
