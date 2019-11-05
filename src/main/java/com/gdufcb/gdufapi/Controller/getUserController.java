package com.gdufcb.gdufapi.Controller;

import com.gdufcb.gdufapi.util.UserMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/19 10:54
 * @Version V1.0
 **/
@Controller
public class getUserController {

    //把对于的http请求转换未https请求
    @RequestMapping(value = "/getGdufMessage", method = RequestMethod.POST)
    @ResponseBody
    public String getGdufMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String method = request.getParameter("method");
        //验证登录信息
        if ("authUser".equals(method)) {
            String user = request.getParameter("username");
            String password = request.getParameter("password");
            String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=" + method + "&xh=" + user + "&pwd=" + password, null);
            return result;
        }
        String token = request.getParameter("token");

        //获取课程信息
        if ("getKbcxAzc".equals(method)) {
            String xh = request.getParameter("xh");
            String xnxqid = request.getParameter("xnxqid");
            String zc = request.getParameter("zc");
            String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=" + method + "&xh=" + xh + "&xnxqid=" + xnxqid + "&zc=" + zc, token);
            return result;
        }
        //获取成绩信息
        if ("getCjcx".equals(method)) {
            String xh = request.getParameter("xh");
            String xnxqid = request.getParameter("xnxqid");
            String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=" + method + "&xh=" + xh + "&xnxqid=" + xnxqid, token);
            return result;
        }
        //获取空教室查询
        if ("getKxJscx".equals(method)) {
            String time = request.getParameter("time");
            String idleTime = request.getParameter("idleTime");
            String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=" + method + "&time=" + time + "&idleTime=" + idleTime, token);
            return result;
        }

        //获取账号信息
        if ("getUserInfo".equals(method)) {
            String xh = request.getParameter("xh");
            String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=getUserInfo&xh=" + xh, token);
            return result;
        }
        //获取当前时间
        if ("getCurrentTime".equals(method)) {
            String currDate = request.getParameter("currDate");
            String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=" + method + "&currDate=" + currDate, token);
            return result;
        }

        return null;
    }


}
