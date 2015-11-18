package com.yaxin.userCenter.service.impl.user;

import com.yaxin.userCenter.common.Encode;
import com.yaxin.userCenter.common.Validate;
import com.yaxin.userCenter.model.BeanFactory;
import com.yaxin.userCenter.model.item.User;
import com.yaxin.userCenter.service.BaseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 修改密码业务类
 */
@Service
public class ChangePWDService extends BaseService{
    @Autowired
    BeanFactory beanFactory;
    @Override
    protected void validate(Map<String, Object> map) throws Exception {
        //本例只做了基本的判断，实际业务中，可能需要更多的业务判断，
        if (!Validate.existByJson(map, "id","oldpwd","passwd")) {
            throw new Exception("传入的参数有误.请检查传入的参数. " + map);
        }
    }

    @Override
    protected Result handle(String req) throws Exception {
        Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(req), Map.class);
        //真正的密码修改业务会加入很多业务方面的判断，这里只实现了最简单的功能
        Result result;
        User user=beanFactory.getUserItem();
        user.setId(Integer.valueOf(String.valueOf(map.get("id"))));
        User resp = (User) user.findById();
        if(resp.getPassword().equals(Encode.stringToMD5(String.valueOf(map.get("oldpwd"))))) {
            user.setPassword(Encode.base64(String.valueOf(map.get("passwd"))));
            return new Result().succeeded("OK");
        }else{
            result = new Result().failed("密码错误.");
        }
        return result;
    }
}
