package com.cleancode.finalexercise;

import java.util.HashSet;

public class PostValidator {

    public ValidationResult validate(Post post, boolean validPath) {

        if(validPath) {

            System.out.println( "Post with ID " + post.id + " is valid." );
            return new ValidationResult();
        }
        else {

            System.out.println( "Post with ID " + post.id + " is NOT valid." );

            HashSet<ValidationError> errors = new HashSet<>();
            errors.add( new ValidationError("Data", "Data connection failure.") );
            errors.add( new ValidationError("Data", "Data parsing failure.") );
            errors.add( new ValidationError("Data", "Data encoding failure.") );

            return new ValidationResult(errors);
        }
        //if-else
    }
    //validate
}
//PostValidator

//eof
