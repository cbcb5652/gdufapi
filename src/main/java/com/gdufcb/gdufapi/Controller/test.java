package com.gdufcb.gdufapi.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdufcb.gdufapi.util.UserMsg;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/26 3:46
 * @Version V1.0
 **/

public class test {
    public static void main(String[] args) throws ParseException, IOException {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String result = UserMsg.interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=getCurrentTime&currDate=" + dateFormat.format(date), "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NzI2MzIwOTQsImF1ZCI6IjE3MTU0MzExOSJ9.4OVclIsnlYox7rwsbpk4o9to4pCIBsjGaDBHUiLOeqo");
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println(jsonObject);




    }
}
