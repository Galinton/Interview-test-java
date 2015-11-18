package com.yaxin.userCenter.model;

import com.yaxin.userCenter.dao.mapper.UserMapper;
import com.yaxin.userCenter.model.item.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wang on 15/11/17.
 * 领域对象工厂，用于装配业务对象。利用工厂封装对象的组装过程，减少开发人员关注spring ioc注入的细节。
 *
 */
@Component
public class BeanFactory {
    @Autowired
    UserMapper userMapper;

    public User getUserItem() {
        return new User(userMapper);
    }
}
