package com.yaxin.userCenter.service.impl.user;

import com.yaxin.userCenter.common.Encode;
import com.yaxin.userCenter.common.ParamWarper;
import com.yaxin.userCenter.common.Validate;
import com.yaxin.userCenter.model.BeanFactory;
import com.yaxin.userCenter.model.item.User;
import com.yaxin.userCenter.service.BaseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 查询用户业务类
 */
@Service
public class FindUserService extends BaseService {

    @Autowired
    BeanFactory beanFactory;

    @Override
    protected void before(JSONObject param) throws Exception {
        super.before(param);
        //本例只做了基本的判断，实际业务中，可能需要更多的业务判断，
        Validate.existByJson(param, "loginname");
        //解码
        param.put("loginname", Encode.base64Decode(String.valueOf(param.get("loginname"))));
        //token权限检查等等，略。
    }

    @Override
    @Cacheable(value = "usercache", keyGenerator = "keyGenerator")
    protected Result handle(JSONObject param) throws Exception {
        if(! ("1".equals(param.get("token")) || "0".equals(param.get("token"))))
            return new Result().failed("未授权用户。通关秘籍 试试token = 1 or 0");
        //真正的查询业务会加入很多业务方面的判断,在返回上也会做些过滤，这里只实现了最简单的功能
        User user = beanFactory.getUserItem();
        user.setLoginname(String.valueOf(param.get("loginname")));
        User resultUser = (User) user.findByName();
        if(resultUser == null)
            return new Result().failed("未查找到用户。");
        //这里简化了token机制，简单实现为1为本人，0为非本人
        if("0".equals(param.get("token"))) {
            JSONObject data = ParamWarper.getInstence().warp("usertype", Integer.valueOf(resultUser.getUserType()))
                    .warp("nickname", resultUser.getUserType())
                    .warp("username", resultUser.getUsername())
                    .warp("comments", resultUser.getComments()).toJson();
            return new Result(data).succeeded("OK");
        }else {
            JSONObject data = ParamWarper.getInstence().warp("usertype", Integer.valueOf(resultUser.getUserType()))
                    .warp("nickname", resultUser.getUserType())
                    .warp("username", resultUser.getUsername())
                    .warp("comments", resultUser.getComments())
                    .warp("quata",1111).toJson(); //暂时这样，数据库忘记建字段了，暂时先这样
            return new Result(data).succeeded("OK");
        }
    }
}
