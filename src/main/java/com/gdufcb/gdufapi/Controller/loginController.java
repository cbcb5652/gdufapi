package com.gdufcb.gdufapi.Controller;

import com.gdufcb.gdufapi.Service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/25 14:36
 * @Version V1.0
 **/
@Controller
public class loginController {

    @Autowired
    private loginService loginService;

    //判断用户是否在线
    @RequestMapping(value = "/ifOnline", method = RequestMethod.GET)
    @ResponseBody
    public String ifOnline(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("user");
        String state = loginService.ifOnline(user);
        return state;
    }

    //用户上线通知
    @RequestMapping(value = "/getOnline", method = RequestMethod.GET)
    @ResponseBody
    public String getOnline(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("user");
        String s = loginService.ifOnline(user);
        System.out.println(s);
        if(s==null){
            int i = loginService.insertUser(user);
            if(i>0){
                return user;
            }
        }
        return user;
    }

    //用户下线通知
    @RequestMapping(value = "/outOnline", method = RequestMethod.GET)
    @ResponseBody
    public void outOnline(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.removeAttribute("user");
        System.out.println("用户下线成功");

    }
}
