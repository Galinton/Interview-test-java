package com.yaxin.userCenter.web.controller;

import com.yaxin.userCenter.service.BaseService;
import com.yaxin.userCenter.service.impl.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    /**
     * 查询用户,只实现了简单查询
     *
     * @param request
     */
    @RequestMapping(value = "/{loginname}", method = RequestMethod.GET)
    public
    @ResponseBody
    BaseService.Result findUser(HttpServletRequest request) {
        String data = request.getParameter("data");
        BaseService.Result result = findUserService.execute(data);
        return result;
    }

    /**
     * 创建用户
     *
     * @param request
     */
    @RequestMapping(value = "/{loginname}", method = RequestMethod.POST)
    public
    @ResponseBody
    BaseService.Result creatUser(HttpServletRequest request) {
        String data = request.getParameter("data");
        BaseService.Result result = creatUserService.execute(data);
        return result;
    }

    /**
     * 修改用户资料
     *
     * @param request
     */
    @RequestMapping(value = "/{loginname}", method = RequestMethod.PUT)
    public
    @ResponseBody
    BaseService.Result changeUser(HttpServletRequest request) {
        String data = request.getParameter("data");
        BaseService.Result result = changeUserService.execute(data);
        return result;
    }

    /**
     * 删除用户
     *
     * @param request
     */
    @RequestMapping(value = "/{loginname}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    BaseService.Result deleteUser(HttpServletRequest request) {
        String data = request.getParameter("data");
        BaseService.Result result = deleteUserService.execute(data);
        return result;
    }

    /**
     * 激活用户
     *
     * @param request
     */
    @RequestMapping(value = "/{loginname}/status", method = RequestMethod.PUT)
    public
    @ResponseBody
    BaseService.Result status(HttpServletRequest request) {
        String data = request.getParameter("data");
        BaseService.Result result = userStatusService.execute(data);
        return result;
    }

    /**
     * 修改密码
     *
     * @param request
     */
    @RequestMapping(value = "/{loginname}/pwd", method = RequestMethod.PUT)
    public
    @ResponseBody
    BaseService.Result changePWD(HttpServletRequest request) {
        String data = request.getParameter("data");
        BaseService.Result result = changePWDService.execute(data);
        return result;
    }





}
