package back.error;

import java.util.Arrays;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, Object ... fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, Arrays.toString(fieldValue)));
    }
}
