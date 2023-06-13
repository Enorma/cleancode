package com.cleancode.finalexercise;

import java.util.HashMap;
import java.util.Map;

public class PostControl_Better {

    //---------------------------------------------------------------------------
    //fields

    private final PostRepository postRepository;
    private final PostValidator postValidator;
    public int postId;
    public TextBox postBody;
    public TextBox postTitle;

    //---------------------------------------------------------------------------
    //constructors

    public PostControl_Better(int _postID, String _postBody, String _postTitle) {

        this.postRepository = new PostRepository();
        this.postValidator  = new PostValidator();
        this.postId         = _postID;
        this.postBody       = new TextBox(_postBody);
        this.postTitle      = new TextBox(_postTitle);
    }
    //PostControl_Better

    //---------------------------------------------------------------------------
    //methods

    public Post buildPost() {

        return new Post(
            this.postId,
            this.postTitle.text.trim(),
            this.postBody.text.trim()
        );
        //Post
    }
    //buildPost

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

    public void displayErrors(ValidationResult results) {

        BulletedList errorList = findErrorSummary();

        for( ValidationError error : results.errors ) {

            Label errorLabel = findError( error.propertyName + "Error" );

            if( errorLabel==null ) errorList.items.add( error.errorMessage );
            else errorLabel.text = error.errorMessage;
        }
        //foreach
    }
    //displayErrors

    public void trySavePost() {

        //--------------------------------------------------------------
        //this next data is mocked, just for the sake of this example:
        boolean postIsValid = false;
        //--------------------------------------------------------------

        Post post = buildPost();

        ValidationResult results = this.postValidator.validate(post, postIsValid);

        if( results.isValid ) postRepository.savePost(post);
        else displayErrors(results);
    }
    //trySavePost

    public void displayPost() {

        //--------------------------------------------------------------
        //this next data is mocked, just for the sake of this example:
        Map<String,String> queryString = new HashMap<>();
        queryString.put("id", "100");
        //--------------------------------------------------------------

        this.postId = Integer.parseInt( queryString.get("id") );
        Post post = postRepository.getPost(this.postId);

        this.postBody.text  = post.body;
        this.postTitle.text = post.title;
    }
    //displayPost

    public void pageLoad() {

        //--------------------------------------------------------------
        //this next data is mocked, just for the sake of this example:
        boolean pageIsPostBack = true;
        //--------------------------------------------------------------

        if( pageIsPostBack ) trySavePost();
        else displayPost();
    }
    //pageLoad
}
//PostControl_Better

//eof
