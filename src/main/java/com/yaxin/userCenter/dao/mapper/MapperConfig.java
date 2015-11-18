package com.yaxin.userCenter.dao.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Autowired
    SqlSessionTemplate sessionTemplate;

    @Bean
    public UserMapper userMapper() {
        return sessionTemplate.getMapper(UserMapper.class);
    }
}
