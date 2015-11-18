package com.yaxin.userCenter.web.controller;

import com.yaxin.userCenter.common.ParamWarper;
import com.yaxin.userCenter.service.BaseService;
import com.yaxin.userCenter.service.impl.user.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    CreatUserService creatUserService;
    @Autowired
    ChangePWDService changePWDService;
    @Autowired
    ChangeUserService changeUserService;
    @Autowired
    DeleteUserService deleteUserService;
    @Autowired
    FindUserService findUserService;
    @Autowired
    UserStatusService userStatusService;

    private static final String TOKEN = "Authorization";
    /**
     * 查询用户,只实现了简单查询
     *
     * @param loginname
     */
    @RequestMapping(value = "/{loginname}", method = RequestMethod.GET)
    public
    @ResponseBody
    String findUser(@PathVariable("loginname") String loginname,HttpServletRequest request) {
        JSONObject data = ParamWarper.getInstence().warp("loginname", loginname)
                .warp("token",request.getHeader(TOKEN)).toJson();
        BaseService.Result result = findUserService.execute(data);
        return result.toString();
    }

    /**
     * 创建用户(需求文档设计有不清楚的地方，不知道loginname是从哪获取，程序中默认从 request body 中获取)
     *
     * @param loginname
     */
    @RequestMapping(value = "/{loginname}", method = RequestMethod.POST)
    public
    @ResponseBody
    String creatUser(@PathVariable("loginname") String loginname, HttpServletRequest request) {
        JSONObject data = ParamWarper.getInstence()
                .warp("loginname", request.getParameter("loginname"))
                .warp("passwd", request.getParameter("passwd"))
                .warp("token", request.getHeader(TOKEN)).toJson();
        BaseService.Result result = creatUserService.execute(data);
        return result.toString();
    }

    /**
     * 修改用户资料
     *
     * @param loginname
     */
    @RequestMapping(value = "/{loginname}", method = RequestMethod.PUT)
    public
    @ResponseBody
    String changeUser(@PathVariable("loginname") String loginname,HttpServletRequest request) {
        JSONObject data = ParamWarper.getInstence().warp("loginname", loginname)
                .warp("usertype", request.getParameter("usertype"))
                .warp("userstatus", request.getParameter("userstatus"))
                .warp("nickname", request.getParameter("nickname"))
                .warp("username",request.getParameter("username"))
                .warp("comments",request.getParameter("comments"))
                .warp("passwd",request.getParameter("passwd"))
                .warp("token",request.getHeader(TOKEN)).toJson();
        BaseService.Result result = changeUserService.execute(data);
        return result.toString();
    }

    /**
     * 删除用户
     *
     * @param loginname
     */
    @RequestMapping(value = "/{loginname}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    String deleteUser(@PathVariable("loginname") String loginname,HttpServletRequest request) {
        JSONObject data = ParamWarper.getInstence().warp("loginname", loginname)
                .warp("token", request.getHeader(TOKEN)).toJson();
        BaseService.Result result = deleteUserService.execute(data);
        return result.toString();
    }

    /**
     * 激活用户
     *
     * @param loginname
     */
    @RequestMapping(value = "/{loginname}/status", method = RequestMethod.PUT)
    public
    @ResponseBody
    String status(@PathVariable("loginname") String loginname,HttpServletRequest request) {
        JSONObject data = ParamWarper.getInstence().warp("loginname", loginname).toJson();
        BaseService.Result result = userStatusService.execute(data);
        return result.toString();
    }

    /**
     * 修改密码
     *
     * @param loginname
     */
    @RequestMapping(value = "/{loginname}/pwd", method = RequestMethod.PUT)
    public
    @ResponseBody
    String changePWD(@PathVariable("loginname") String loginname,HttpServletRequest request) {
        JSONObject data = ParamWarper.getInstence().warp("loginname", loginname)
                .warp("oldpwd",request.getParameter("oldpwd"))
                .warp("passwd",request.getParameter("passwd"))
                .warp("token", request.getHeader(TOKEN)).toJson();
        BaseService.Result result = changePWDService.execute(data);
        return result.toString();
    }


}
