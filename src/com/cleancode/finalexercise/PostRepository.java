package com.cleancode.finalexercise;

import java.util.Set;

public class PostRepository {

    private final PostDbContext dbContext;

    public PostRepository() {

        this.dbContext = new PostDbContext();
    }
    //PostRepository

    public Set<Post> getPosts() {

        return this.dbContext.getPosts();
    }
    //getPosts

    public void addPost(Post post) {

        this.dbContext.addPost(post);
    }
    //addPost

    public void saveChanges() {

        this.dbContext.saveChanges();
    }
    //saveChanges

    public Post getPost(int postId) {

        return this.getPosts().stream()
            .filter( p -> p.id==postId )
            .findFirst()
            .orElse( new Post(-1, "default", "default") );
        //stream
    }
    //getPost

    public void savePost(Post post) {

        this.addPost(post);
        this.saveChanges();
    }
    //savePost
}
//PostRepository

//eof
