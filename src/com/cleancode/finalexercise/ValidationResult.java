package com.cleancode.finalexercise;

import java.util.HashSet;
import java.util.Set;

public class ValidationResult {

    public boolean isValid;
    public Set<ValidationError> errors;

    public ValidationResult() {
        this.isValid = true;
        this.errors = new HashSet<>();
    }
    //ValidationResult

    public ValidationResult(HashSet<ValidationError> _errors) {
        this.isValid = false;
        this.errors = _errors;
    }
    //ValidationResult
}
//ValidationResult

//eof
