package com.gdufcb.gdufapi.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gdufcb.gdufapi.util.UserMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/31 23:57
 * @Version V1.0
 **/
@Controller
public class searchController {

    //搜索课表
    @RequestMapping(value = "/searchRepport", method = RequestMethod.GET)
    @ResponseBody
    public  ArrayList searchRepport(HttpServletRequest request) throws IOException {
        String all = request.getParameter("zd");
        String zd = ".*";
        for(int i =0;i<all.length();i++){
            zd += (all.charAt(i)+".*");
        }
        String xh = request.getParameter("xh");
        String token = request.getParameter("token");
        String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=getCjcx&xh=" + xh , token);
        result = result.substring(1,result.length()-3);
        String[] split = result.split("},");
        ArrayList list = new ArrayList();
        for (int i = 0; i < split.length; i++) {
            if(i!=split.length-1){
                split[i] +="}";
            }
            JSONObject jsonObject = JSON.parseObject(split[i]);
            if(Pattern.matches(zd,jsonObject.getString("kcmc"))){
                list.add(jsonObject);
            }

        }
        return list;
    }





}
