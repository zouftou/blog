package com.alluz.blog.web.exc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    /**
     * Constructs a new ResourceNotFoundException with the specified resource name, field name, and field value.
     *
     * @param resourceName the name of the resource (e.g., "User", "Product")
     * @param fieldName the name of the field used to identify the resource (e.g., "id", "email")
     * @param fieldValue the value of the field that was searched for (e.g., "1", "example@example.com")
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    /**
     * Returns the name of the resource that was not found.
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Returns the name of the field used to identify the resource.
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Returns the value of the field that was searched for.
     */
    public Object getFieldValue() {
        return fieldValue;
    }

    /**
     * Returns a detailed message about the resource that was not found.
     */
    @Override
    public String getMessage() {
        return String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);
    }
}