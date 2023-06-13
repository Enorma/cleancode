package com.cleancode.finalexercise;

public class FinalExercise {

    public static void runExercise() {

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("FINAL EXERCISE\n");

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("About the exercise\n");

        //we can test the not-refactored code with:
        var postcontrol1 = new PostControl_Bad(1, "hola", "mundo");
        postcontrol1.pageLoad();

        //all course lessons are applied here.
        //refactor the PostControl_Bad class into the PostControl_Better class.

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Things to Refactor\n");

        //NOTE: at the start, the TextBox and PostRepository classes don't exist.

        //the first and most obvious refactor is in fact
        //fixing a mistake.

        //the postBody and postTitle fields are supposed to represent
        //text boxes in a GUI. Not labels.
        //So we'll need to mock a TextBox class for that.
        //(make it extremely simple, just a string field and a constructor)

        //then change postBody and postTitle into the TextBox class.
        //other Label's can be left as-is.

        //second, since the PostControl_Better class is concerned
        //with presentation (displaying a form to the user)
        //then there shouldn't be any DB-related code in it.
        //therefore, the dbContext field should be moved somewhere else.

        //since our storage will just hold a bunch of posts
        //we can model our DB as a post repository.
        //so, we create the PostRepository class,
        //and give it a PostDbContext field and a constructor.

        //at this point, inside PostControl_Better,
        //we can replace the PostDbContext field with a PostRepository field.
        //then we can refactor those places where there's DB access

        //to do this, we need to find those places where dbContext is used.
        //there's one for saving a post and one for finding a post.
        //afterward, we extract those blocks of logic into their own methods
        //and place those methods inside the PostRepository class,
        //since they concern DB access, not presentation.

        //the first one is easy to extract.
        //it is just one line to add a new post
        //and the next one, to save the changes.
        //we extract that into the savePost() method
        //and pass the post to it as arg.

        //the second one is further below.
        //it includes a stream block and a Predicate lambda.
        //the querystring needs to be mocked closer, because the id is needed.

        //get the id as an integer from the querystring
        //then extract the whole stream block into a new getPost() method
        //inside PostRepository.
        //when calling it, pass the id as arg.

        //we're done extracting code into other classes!
        //all that's left will be contained in the PostControl_Better class.

        //now, this becomes a matter of extracting long if/else clauses
        //into separate methods, and identifying places where a block of code
        //does more than one thing.

        //first of all, there's a PostValidator being initialized
        //in the middle of the code.
        //for these kinds of auxiliary classes, it's better to declare them
        //as fields and initialize them in the constructor instead.
        //so we now have the postValidator field.

        //then, the new Post() constructor call
        //right below where the validator was being initialized
        //can be extracted into its own buildPost() method.

        //the else clause of the inner if-else block concerns displaying
        //a list of errors (as Label GUI objects) on the screen.

        //then, it can be extracted into its own method.
        //we'll call it displayErrors().
        //some variable names should be made clearer as well,
        //like "failure" should be "error", for example.

        //the else clause of the outer if-else block concerns
        //displaying a post on the screen.
        //it mocks the querystring,
        //then calls the getPost() method we extracted earlier.
        //and sets the text on both text boxes.
        //we can extract the whole block into its own
        //displayPost() method.

        //the if clause of the outer if-else block
        //concerns creating a post, validating it,
        //and if valid, saving it.
        //if not, display the errors.

        //this block has already been made short
        //through our previous refactorings
        //but we can extract it anyway.
        //we'll make the new trySavePost() from it.

        //finally, our pageLoad() method is just 2 or 3 lines long!

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Final Refactored Code\n");

        //the final refactored PostControl_Better class can be tested with:
        var postcontrol2 = new PostControl_Better(1, "hola", "mundo");
        postcontrol2.pageLoad();

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("END OF THE COURSE!\n");

        //
    }
    //runExercise
}
//FinalExercise

//eof
