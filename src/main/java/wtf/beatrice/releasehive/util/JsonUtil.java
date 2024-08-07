package wtf.beatrice.releasehive.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wtf.beatrice.releasehive.model.ApiError;

public class JsonUtil
{

    private JsonUtil() {
        throw new AssertionError("Utility class");
    }

    private static final Logger LOGGER = LogManager.getLogger(JsonUtil.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String convertToJson(Object input) {
        try {
            return MAPPER.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            LOGGER.error(e);
            return e.getMessage();
        }
    }

    public static String spawnJsonError(String errorMessage) {
        ApiError apiError = new ApiError();
        apiError.setMessage(errorMessage);
        String error = convertToJson(apiError);
        LOGGER.error(error);
        return error;
    }
}
