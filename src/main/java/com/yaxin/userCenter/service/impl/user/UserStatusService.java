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
 * 修改用户状态业务类
 */
@Service
public class UserStatusService extends BaseService {

    @Autowired
    BeanFactory beanFactory;

    @Override
    protected void before(JSONObject param) throws Exception {
        //本例只做了基本的判断，实际业务中，可能需要更多的业务判断，
        Validate.existByJson(param, "loginname");
        //解码
        param.put("loginname", Encode.base64Decode(String.valueOf(param.get("loginname"))));
    }

    @Override
    protected Result handle(JSONObject param) throws Exception {
        //真正的修改用户状态业务类加入很多业务方面的判断,这里只实现了最简单的功能
        User user=beanFactory.getUserItem();
        user.setLoginname(String.valueOf(param.get("loginname")));
        if(user.findByName() != null) {
            user.setStatus("1");
            user.update();
            return new Result().succeeded("OK");
        }else{
            return new Result().failed("没有找到该用户");
        }
    }
}
