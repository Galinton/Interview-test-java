package com.yaxin.userCenter.service.impl.user;

import com.yaxin.userCenter.common.Validate;
import com.yaxin.userCenter.model.BeanFactory;
import com.yaxin.userCenter.model.item.User;
import com.yaxin.userCenter.service.BaseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 修改用户状态业务类
 */
@Service
public class UserStatusService extends BaseService {

    @Autowired
    BeanFactory beanFactory;

    @Override
    protected void validate(Map<String, Object> map) throws Exception {
        //本例只做了基本的判断，实际业务中，可能需要更多的业务判断，
        if (!Validate.existByJson(map, "id","status")) {
            throw new Exception("传入的参数有误.请检查传入的参数. " + map);
        }
    }

    @Override
    protected Result handle(String req) throws Exception {
        Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(req), Map.class);
        //真正的修改用户状态业务类加入很多业务方面的判断,这里只实现了最简单的功能
        User user=beanFactory.getUserItem();
        user.setId(Integer.valueOf(String.valueOf(map.get("id"))));
        user.setStatus(String.valueOf(map.get("status")));
        user.update();
        return new Result().succeeded("OK");
    }
}