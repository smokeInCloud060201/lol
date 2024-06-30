package vn.com.lol.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;

@Slf4j
public class JsonUtil {
    private static ObjectMapper mapper;
    private static ObjectWriter writer;

    private JsonUtil() {}

    private static void initialize() {
        if (mapper != null) {
            return;
        }

        mapper = new ObjectMapper()
                .registerModules(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.INDENT_OUTPUT, true);

        DefaultPrettyPrinter pp = new DefaultPrettyPrinter()
                .withoutSpacesInObjectEntries()
                .withArrayIndenter(new DefaultPrettyPrinter.NopIndenter())
                .withObjectIndenter(new DefaultPrettyPrinter.NopIndenter());
        writer = new ObjectMapper()
                .registerModules(new JavaTimeModule())
                .writer().with(pp);
    }

    public static void setMapper(Module module) {
        initialize();
        mapper.registerModule(module);
    }

    public static JsonNode convertStringToJsonNode(String jsonString) throws IOException {
        initialize();
        return mapper.readTree(jsonString);
    }

    public static String stringify(Object data) {
        initialize();
        try {
            return writer.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("EXCEPTION WHEN PARSE OBJECT TO STRING {}", e.getMessage());
        }
        return null;
    }

    public static void stringify(Object data, OutputStream output) {
        initialize();
        try {
            writer.writeValue(output, data);
        } catch (IOException e) {
            log.error("EXCEPTION WHEN PARSE OBJECT TO STRING {}", e.getMessage());
        }
    }

    /**
     * Gets object from json string. Object MUST have constructor with no parameter
     *
     * @param <T>   the type parameter
     * @param clazz class to convert
     * @param json  the json
     * @return the object from json string
     * @throws JsonProcessingException the json processing exception
     */
    public static <T> T getObjectFromJsonString(Class<T> clazz, String json) throws JsonProcessingException {
        initialize();
        return mapper.readValue(json, clazz);
    }

    public static <T> T getObjectFromJsonString(TypeReference<T> clazz, String json) {
        initialize();
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("EXCEPTION WHEN PARSE STRING TO OBJECT {}", e.getMessage());
        }
        return null;
    }

    private static <T> T convert(Object obj, TypeReference<T> typeReference) {
        initialize();
        return mapper.convertValue(obj, typeReference);
    }

    public static Map<String, Object> convertToMap(Object obj) {
        return convert(obj, new TypeReference<>() {});
    }

    public static Set<String> convertToSet(Object obj) {
        return convert(obj, new TypeReference<>() {});
    }
}
