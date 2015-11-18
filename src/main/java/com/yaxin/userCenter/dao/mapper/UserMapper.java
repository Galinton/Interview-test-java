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

	public void deleteByName(String username);

	public User findById(Integer id);

	public User findByName(String username);

	public void update(User user);

}
