package com.yaxin.userCenter.model;

/**
 * Created by wang on 15/11/17.
 */
public interface Repository {

    public abstract void create();
    public abstract void update();
    public abstract void delete();
    public abstract Repository findById();
    public abstract Repository findByName();
}
