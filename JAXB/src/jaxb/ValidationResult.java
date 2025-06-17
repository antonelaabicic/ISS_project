package jaxb;

import java.util.List;

public class ValidationResult {
    public boolean isValid;
    public List<String> errors;

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }
}
