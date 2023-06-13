package com.cleancode.finalexercise;

import java.util.Set;

public class PostDbContext {

    public DbSet<Post> posts;

    public PostDbContext() {

        this.posts = new DbSet<>();
    }
    //PostDbContext

    public void saveChanges() {

        System.out.println("changes saved to post db.");
    }
    //saveChanges

    public Set<Post> getPosts() {

        return this.posts.getInnerSet();
    }
    //getPosts

    public void addPost(Post item) {

        this.posts.add(item);
    }
}
//PostDbContext

//eof
