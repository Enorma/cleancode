package com.cleancode.finalexercise;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.util.HashSet;
import java.util.Set;

public class DbSet<T> {

    public Set<T> innerSet;

    public DbSet() {

        this.innerSet = new HashSet<>();
    }
    //DbSet

    public void add(T item) {

        this.innerSet.add(item);
    }
    //add

    public Set<T> getInnerSet() {

        return this.innerSet;
    }
    //getInnerSet
}
//DbSet

//eof
