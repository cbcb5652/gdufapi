package com.gdufcb.gdufapi.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdufcb.gdufapi.Pojo.userIndex;
import com.gdufcb.gdufapi.Service.userService;
import com.gdufcb.gdufapi.util.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/26 2:35
 * @Version V1.0
 **/
@Controller
public class IndexController {

    @Autowired
    private userService userService;



    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    @ResponseBody
    public String addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getParameter("token");
        String user = request.getParameter("user");
        String addUser = request.getParameter("addUser");

        if(!userService.exitUser(user,addUser)){
            userIndex userIndex = new userIndex();
            userIndex.setUser(user);
            userIndex.setAddUser(addUser);
            userService.insertUser(userIndex);
        }
        return "ok";
    }


    @RequestMapping(value = "deleteUser",method = RequestMethod.GET)
    @ResponseBody
    public String deleteUser(HttpServletRequest request){
        String user = request.getParameter("user");
        String deleteUser = request.getParameter("deleteUser");
        if(userService.exitUser(user,deleteUser)){
            userIndex userIndex = new userIndex();
            userIndex.setUser(user);
            userIndex.setAddUser(deleteUser);
            userService.deleteUser(userIndex);
        }
        return "ok";

    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public Set getUser(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("user");
        Set set = userService.findUser(user);
        return set;
    }

    //获取当前首页上课时间
    @RequestMapping(value = "/getNowClass" ,method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getNowClass(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        Date date = new Date();
        JSONObject jso = new JSONObject();
        String user = request.getParameter("user");
        String token = request.getParameter("token");
        String id = request.getParameter("id");
        //获取当前周数
        String currDate = getCurrentTime(token);
        String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=getCurrentTime&currDate=" + currDate, token);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String zc = jsonObject.getString("zc");
        String xnxqid = jsonObject.getString("xnxqh");
        //获取当前周次课表
        String currentTimeTable = getKbcxAzc(user,xnxqid,zc,token);
        //获取当前时间24小时制
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String nowTime = dateFormat.format(date);
        Date now = dateFormat.parse(nowTime);
        //获取当前周几
        String xqj = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1);
//        JSONObject jsonObject1 = JSONObject.parseObject(currentTimeTable);
        currentTimeTable = currentTimeTable.substring(1,currentTimeTable.length()-3);
        String[] myTimeTable = currentTimeTable.split("},");
       for(int i=0;i<myTimeTable.length-1;i++){
           myTimeTable[i] = myTimeTable[i]+"}";
       }


        String jlxjksj ="";
        String jlxksj = "";
        String xjk = "";
        String xjkjs = "";
        String zzsk = "";
        String zzskjs ="";

        for(int i =0;i<myTimeTable.length;i++){
            JSONObject jo = JSONObject.parseObject(myTimeTable[i]);
            String kc = jo.getString("kcsj");
            String all = "";
           if(kc.indexOf("0")<0){
               for(int j=0;j<kc.length();j++){
                   char c = kc.charAt(j);
                   if(j%2!=0){
                       all +="0";
                   }
                   all += c;
               }
           }else {
               all = kc;
           }
            String[] kcsj = all.split("0");
            if(kcsj[0].equals(xqj)){
                Date kssj = dateFormat.parse(jo.getString("kssj"));
                Date jssj = dateFormat.parse(jo.getString("jssj"));
                long betweenkssj = (kssj.getTime()-now.getTime())/1000;//除以1000是为了转换成秒
                long betweenjssj = (jssj.getTime()-now.getTime())/1000;
                long hour;
                long minute;

                if(betweenkssj>0&&betweenjssj>0){
                    hour = betweenkssj%(24*3600)/3600;
                    minute = betweenkssj%3600/60;
                    jlxjksj = hour+"小时"+minute+"分";
                    xjk = jo.getString("kcmc");
                    xjkjs = jo.getString("jsmc");

                    break;
                }else if(betweenkssj<0&&betweenjssj>0){
                    hour = betweenjssj%(24*3600)/3600;
                    minute = betweenjssj%3600/60;
                    jlxksj = hour+"小时"+minute+"分";
                    zzsk = jo.getString("kcmc");
                    zzskjs = jo.getString("jsmc");
                    xjk = JSONObject.parseObject(myTimeTable[i+1]).getString("kcmc");
                    xjkjs = JSONObject.parseObject(myTimeTable[i+1]).getString("jsmc");
                    break;
                }
            }
        }
        jso.put("jlxjksj",jlxjksj);
        jso.put("jlxksj",jlxksj);
        jso.put("xjk",xjk);
        jso.put("xjkjs",xjkjs);
        jso.put("zzsk",zzsk);
        jso.put("zzskjs",zzskjs);
        jso.put("user",user);
        jso.put("id",id);


        return jso;

    }

    //获取当前学期时间的函数
    public  String getCurrentTime(String token){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd ");
       String result = dateFormat.format(date);
        return result;

    }

   //获取对应学号课表的信息
    public String getKbcxAzc(String user,String xnxqid,String zc,String token) throws IOException {
        String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=getKbcxAzc&xh=" + user + "&xnxqid=" + xnxqid + "&zc=" + zc, token);
        return result;
    }




}
