package ir.co.isc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Util {

    public static Object getRequestBody(Object[] methodArgs) {

        for (int i = 0; i < methodArgs.length; i++) {

            if (!(methodArgs[i] instanceof Long)) {
                return methodArgs[i];
            }

        }
        return null;

    }

    public static String prettyJson(Object json) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        if (json == null) return "{}";
        try {
            String pretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            return addIndent(pretty);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    private static String addIndent(String string) {
        return string.replaceAll("\n", "\n\t");
    }

}
