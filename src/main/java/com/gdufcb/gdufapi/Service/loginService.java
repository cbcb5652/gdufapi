package com.gdufcb.gdufapi.Service;

import com.gdufcb.gdufapi.Mapper.loginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/27 22:11
 * @Version V1.0
 **/
@Service
public class loginService {

    @Autowired
    private loginMapper loginMapper;

    //用户上线
    public int insertUser(String user){
        int count = loginMapper.insertUser(user);
        if(count>0){
            System.out.println("用户登录插入成功");
        }else {
            System.out.println("用户登录插入失败");
        }
        return count;
    }




    //改变用户登录状态
    public int changestate(String user){
        int count = loginMapper.changestate(user);
        if(count>0){
            System.out.println("更新状态成功");
        }else {
            System.out.println("更新状态失败");
        }
        return count;
    }

    //判断用户是否在线
    public String  ifOnline(String user) {
       String state =  loginMapper.ifOnline(user);
       return  state;
    }
}
