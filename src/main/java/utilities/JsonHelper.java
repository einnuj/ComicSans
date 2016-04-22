package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * A Class that de/serializes between JSON and Objects.
 * Created by einnuj on 4/12/2016.
 */
public class JsonHelper {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String objectToJson (Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public static <T> T jsonToObject (String json, Class<T> tClass) throws IOException {
        return mapper.readValue(json, tClass);
    }

    public static <T> List jsonArrayToListGeneric (String json, Class<T> tClass) throws IOException {
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, tClass));
    }
}
