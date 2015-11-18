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
 * 修改用户资料业务类
 */
@Service
public class ChangeUserService extends BaseService {

    @Autowired
    BeanFactory beanFactory;

    @Override
    protected void before(JSONObject param) throws Exception {
        //本例只做了基本的判断，实际业务中，可能需要更多的业务判断，
        Validate.existByJson(param, "loginname","token");
        //解码
        param.put("loginname", Encode.base64Decode(String.valueOf(param.get("loginname"))));
    }

    @Override
    protected Result handle(JSONObject param) throws Exception {
        if(! ("1".equals(param.get("token")) || "0".equals(param.get("token"))))
            return new Result().failed("未授权用户。通关秘籍 试试token = 1 or 0");
        //真正的修改业务会加入很多业务方面的判断，这里只实现了最简单的功能
        User user=beanFactory.getUserItem();
        user.setLoginname(String.valueOf(param.get("loginname")));
        if(user.findByName() == null)
            return new Result().failed("用户不存在");
        //这里简化了token机制，简单实现为1为管理员，0为用户，默认都是登陆有修改权限
        if("0".equals(String.valueOf(param.get("token")))){
            user.setNickname(String.valueOf(param.get("nickname")));
            user.setComments(String.valueOf(param.get("comments")));
        } else {
            user.setNickname(String.valueOf(param.get("nickname")));
            user.setComments(String.valueOf(param.get("comments")));
            user.setUserType(String.valueOf(param.get("usertype")));
            user.setStatus(String.valueOf(param.get("userstatus")));
            user.setUsername(String.valueOf(param.get("username")));
            user.setPassword(String.valueOf(param.get("passwd")));
        }
        user.update();
        return new Result().succeeded("OK");
    }
}
