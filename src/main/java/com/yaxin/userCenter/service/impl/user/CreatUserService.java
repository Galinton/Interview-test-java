package com.yaxin.userCenter.service.impl.user;

import com.yaxin.userCenter.common.Encode;
import com.yaxin.userCenter.common.Validate;
import com.yaxin.userCenter.model.BeanFactory;
import com.yaxin.userCenter.model.item.User;
import com.yaxin.userCenter.service.BaseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建用户业务类
 */
@Service
public class CreatUserService extends BaseService{

    @Autowired
    BeanFactory beanFactory;

    @Override
    protected void before(JSONObject param) throws Exception {
        //本例只做了基本的判断，实际业务中，可能需要更多的业务判断，比如对password规则等判断
        Validate.existByJson(param,"token","loginname","passwd");
    }

    @Override
    protected Result handle(JSONObject param) throws Exception {
        if(!"1".equals(param.get("token")))
            return new Result().failed("未授权用户。通关秘籍 试试token = 1");
        //真正的创建业务会加入很多业务方面的判断，这里只实现了最简单的功能
        User user=beanFactory.getUserItem();
        user.setLoginname(String.valueOf(param.get("loginname")));
        user.setPassword(String.valueOf(param.get("passwd")));
        user.setStatus("0");
        user.setUserType("0");
        if(user.findByName() == null) {
            user.create();
            return new Result().succeeded("OK");
        }else{
            return new Result().failed("用户已存在");
        }
    }
}
