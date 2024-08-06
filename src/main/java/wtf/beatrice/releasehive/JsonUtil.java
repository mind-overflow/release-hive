package wtf.beatrice.releasehive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonUtil
{
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
}
