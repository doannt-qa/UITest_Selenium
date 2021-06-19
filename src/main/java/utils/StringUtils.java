package utils;

import java.util.Map;

public class StringUtils {
    public static final String mapFormat(String template, Map<String, String> placeHolderNameToValue) {
	    String result = template;
	    for (String key : placeHolderNameToValue.keySet()) {
	    	result = result.replace(String.format("{%s}", key), placeHolderNameToValue.get(key));
	    }
	    return result;
	}
}
