package cn.leyizuo.www.springboot_jpa.controller;

import cn.leyizuo.www.springboot_jpa.entity.UserInfo;
import cn.leyizuo.www.springboot_jpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping(value = "/test")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserInfo userInfo;

    @Resource
    private UserService userService;

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping(value = "/getUserList")
    public List<UserInfo> getUserList() {
        return userService.getUserList();
    }

    @GetMapping(value = "/getUserInfo")
    public List<UserInfo> getUserInfoByName(@RequestParam("name") String name) {
        return userService.getUserByName(name);
    }

    @GetMapping(value = "/getCurrentUserList")
    public List<UserInfo> getCurrentUserList() {
        return userService.getCurrentUserList();
    }

    @GetMapping(value = "/getPageUserList")
    public Page<UserInfo> getPageUserList() {
        return userService.getPageUserList();
    }

    @PutMapping(value = "/addUserInfo")
    public UserInfo addUserInfo(UserInfo userInfo) {
        return userService.addUserInfo(userInfo);
    }

    @PostMapping(value = "/updateUserInfo")
    public UserInfo updateUserInfo(UserInfo userInfo) {
        return userService.updateUserInfoById(userInfo);
    }

    @PostMapping(value = "/deleteUserInfo")
    public void deleteUserInfo(@RequestParam("id") Long id) {
        userService.deleteUserInfoById(id);
    }

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));/*TimeZone时区，解决差8小时的问题*/
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}