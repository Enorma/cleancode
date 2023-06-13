package com.cleancode.finalexercise;

import java.util.HashMap;
import java.util.Map;

public class PostControl_Bad {

    //---------------------------------------------------------------------------
    //fields

    private PostDbContext dbContext;
    public int postId;
    public Label postBody;
    public Label postTitle;

    //---------------------------------------------------------------------------
    //constructors

    public PostControl_Bad(int _postID, String _postBody, String _postTitle) {

        this.dbContext = null;
        this.postId = _postID;
        this.postBody = new Label(_postBody);
        this.postTitle = new Label(_postTitle);
    }
    //PostControl_Bad

    //---------------------------------------------------------------------------
    //methods

    public static BulletedList findErrorSummary() {

        BulletedList bList = new BulletedList();

        bList.items.add("• Data connection failure.");
        bList.items.add("• Data parsing failure.");
        bList.items.add("• Data encoding failure.");

        return new BulletedList();
    }
    //findErrorSummary

    public static Label findError(String property) {

        return new Label(property);
    }
    //findError

    public void pageLoad() {

        this.dbContext = new PostDbContext();

        //--------------------------------------------------------------
        //this next data is mocked, just for the sake of this example:

        Map<String,String> queryString = new HashMap<>();
        queryString.put("id", "100");

        boolean pageIsPostBack = true;
        boolean postIsValid = false;
        //--------------------------------------------------------------

        if(pageIsPostBack) {

            PostValidator validator = new PostValidator();

            Post entity = new Post( //Map form fields to entity properties
                this.postId,
                this.postTitle.text.trim(),
                this.postBody.text.trim()
            );
            //Post

            ValidationResult results = validator.validate(entity, postIsValid);

            if(results.isValid) {
                // Save to the database and continue to the next page
                this.dbContext.posts.add(entity);
                this.dbContext.saveChanges();
            }
            else {

                BulletedList summary = findErrorSummary();

                // Display errors to the user
                for(ValidationError error : results.errors) {

                    Label errorLabel = findError( error.propertyName + "Error" );

                    if( errorLabel==null ) {

                        summary.items.add( error.errorMessage );
                    }
                    else {

                        errorLabel.text = error.errorMessage;
                    }
                    //if-else
                }
                //foreach
            }
            //if-else
        }
        else {

            // Display form

            this.postId = Integer.parseInt( queryString.get("id") );

            Post entity = dbContext.getPosts().stream()
                .filter(p -> p.id==this.postId)
                .findFirst()
                .orElse( new Post(-1, "default", "default") );
            //stream

            this.postBody.text  = entity.body;
            this.postTitle.text = entity.title;
        }
        //if-else
    }
    //pageLoad
}
//PostControl_Bad

//eof
