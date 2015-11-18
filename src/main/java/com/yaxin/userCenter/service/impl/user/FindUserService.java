package com.yaxin.userCenter.service.impl.user;

import com.yaxin.userCenter.common.Validate;
import com.yaxin.userCenter.model.BeanFactory;
import com.yaxin.userCenter.model.item.User;
import com.yaxin.userCenter.service.BaseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 查询用户业务类
 */
@Service
public class FindUserService extends BaseService {

    @Autowired
    BeanFactory beanFactory;
    @Override
    protected void validate(Map<String, Object> map) throws Exception {
        //本例只做了基本的判断，实际业务中，可能需要更多的业务判断，
        if (!Validate.existByJson(map, "loginname")) {
            throw new Exception("传入的参数有误.请检查传入的参数. " + map);
        }
    }

    @Override
    @Cacheable(value = "usercache",keyGenerator = "keyGenerator")
    protected Result handle(String req) throws Exception {
        Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(req), Map.class);
        //真正的查询业务会加入很多业务方面的判断,在返回上也会做些过滤，这里只实现了最简单的功能
        User user=beanFactory.getUserItem();
        user.setUsername(String.valueOf(map.get("loginname")));
        User resp = (User) user.findByName();
        resp.setPassword(null);
        String data  = JSONObject.fromObject(resp).toString();
        return new Result().succeeded("OK");
    }
}
