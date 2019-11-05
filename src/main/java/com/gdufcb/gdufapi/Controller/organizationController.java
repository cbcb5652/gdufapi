package com.gdufcb.gdufapi.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdufcb.gdufapi.Pojo.JoinOrganization;
import com.gdufcb.gdufapi.Pojo.organization;
import com.gdufcb.gdufapi.Service.organizationService;
import com.gdufcb.gdufapi.util.UserMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/11/1 16:21
 * @Version V1.0
 **/
@Controller
public class organizationController {

    @Autowired
    organizationService organizationService;

    //申请组织
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    @ResponseBody
    public String createOrganization(organization organization){
        if(organizationService.ifOrganization(organization.getUserName())==null){
            int i = organizationService.insertOrganization(organization);
            if(i>0){
                return "ok";
            }else {
                return "fail";
            }
        }else {
            return "exist";
        }
    }

    //搜索框搜索组织函数
    @RequestMapping(value = "/findOrganization",method = RequestMethod.POST)
    @ResponseBody
    public Set findOrganization(String organization){
        Set set = organizationService.find(organization);
        return set;
    }

    //我自己的组织
    @RequestMapping(value = "/myOrganization",method = RequestMethod.POST)
    @ResponseBody
    public List myOrganization(String studentId){
        String user = studentId;
        List<organization> myOrganization = organizationService.findMyOrganization(user);
        return  myOrganization;
    }

    //加入组织
    @RequestMapping(value = "/joinOrganization",method = RequestMethod.POST)
    @ResponseBody
    public String joinOrganization(HttpServletRequest request, JoinOrganization joinOrganization){

        if(organizationService.ifexist(joinOrganization)==null){
            int i = organizationService.joinOrganization(joinOrganization);
            if(i>0){
                return "ok";
            }else {
                return "fail";
            }
        }
        return "exist";
    }


    //查询组织对应日期的无课表
    @RequestMapping(value = "/getNoTimeTable",method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getNoTimeTable(HttpServletRequest request) throws IOException {
        String token = request.getParameter("token");
        String organizationName = request.getParameter("organizationName");
        String code = request.getParameter("code");
        List<JoinOrganization> list = organizationService.findAllUser(organizationName);
        //获取当前周次
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=getCurrentTime&currDate=" + dateFormat.format(date), token);
        JSONObject jsonObject = JSON.parseObject(result);
        String zc =  jsonObject.getString("zc");
        String xnxqid = jsonObject.getString("xnxqh");

        //所有有空的人的信息
        List<JSONObject> li = new ArrayList<>();
        for (JoinOrganization joinOrganization : list) {
            JSONObject alljson = new JSONObject();
            String xh = joinOrganization.getStudentId();
            String kbxx = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=getKbcxAzc&xh=" + xh + "&xnxqid=" + xnxqid + "&zc=" + zc, token);
            kbxx = kbxx.substring(1,kbxx.length()-3);
            String[] myTimeTable = kbxx.split("},");
            for(int i=0;i<myTimeTable.length-1;i++){
                myTimeTable[i] = myTimeTable[i]+"}";
            }
            boolean flag = true;
            for (String s : myTimeTable) {
                JSONObject jsonObject1 = JSON.parseObject(s);
                if(jsonObject1.getString("kcsj").equals(code)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                alljson.put("xh",joinOrganization.getStudentId());
                alljson.put("name",joinOrganization.getUsername());
                li.add(alljson);
            }
        }
        return li;
    }






}
