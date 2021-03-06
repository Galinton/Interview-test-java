package com.yaxin.userCenter.dao.mapper;

import com.yaxin.userCenter.model.item.User;

/**
 *
 * mybatis 实现接口和配置文件
 *
 */
public interface UserMapper {

	public void insert(User user);

	public void deleteById(Integer id);

	public void deleteByName(String loginname);

	public User findById(Integer id);

	public User findByName(String loginname);

	public void update(User user);

}
