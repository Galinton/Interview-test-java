package com.yaxin.userCenter.model.item;

import com.yaxin.userCenter.dao.mapper.UserMapper;
import com.yaxin.userCenter.model.Repository;

public class User implements Repository {

    //持有dao层的实例，从datasources获得
    UserMapper userMapper;

    private Integer id;
    private String loginname;
    private String username;
    private String nickname;
    private String password;
    private String comments;
    private String userType;
    private String status;

    public User(){}

    public User(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void create() {
        userMapper.insert(this);
    }

    @Override
    public void update() {
        userMapper.update(this);
    }

    @Override
    public void delete() {
        userMapper.deleteByName(this.loginname);
    }

    @Override
    public Repository findById() {
        return userMapper.findById(this.id);
    }

    @Override
    public Repository findByName() {
        return userMapper.findByName(this.loginname);
    }

    /* ------------------------------------------------------------------------------ */
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
}
