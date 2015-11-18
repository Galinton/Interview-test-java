package com.yaxin.userCenter.service.impl.user;

import com.yaxin.userCenter.common.Encode;
import com.yaxin.userCenter.common.Validate;
import com.yaxin.userCenter.model.BeanFactory;
import com.yaxin.userCenter.model.Repository;
import com.yaxin.userCenter.model.item.User;
import com.yaxin.userCenter.service.BaseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改密码业务类
 */
@Service
public class ChangePWDService extends BaseService{
    @Autowired
    BeanFactory beanFactory;
    @Override
    protected void before(JSONObject param) throws Exception {
        //本例只做了基本的判断，实际业务中，可能需要更多的业务判断，
        Validate.existByJson(param, "token","loginname","oldpwd","passwd");
        //解码
        param.put("loginname", Encode.base64Decode(String.valueOf(param.get("loginname"))));
        //token权限检查
    }

    @Override
    protected Result handle(JSONObject param) throws Exception {
        if(!"1".equals(param.get("token")))
            return new Result().failed("无授权 通关秘籍试试 tokeng = 1");
        //真正的密码修改业务会加入很多业务方面的判断，这里只实现了最简单的功能
        User user=beanFactory.getUserItem();
        user.setLoginname(String.valueOf(param.get("loginname")));
        Repository repository = user.findByName();
        if(repository == null)
            return new Result().failed("没有找到用户");
        if(!((User)repository).getPassword().equals(String.valueOf(param.get("oldpwd"))))
            return new Result().failed("密码错误.");
        user.setPassword(String.valueOf(param.get("passwd")));
        user.update();
        return new Result().succeeded("OK");
    }
}
