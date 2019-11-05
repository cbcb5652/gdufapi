package com.gdufcb.gdufapi.Service;

import com.gdufcb.gdufapi.Mapper.userMapper;
import com.gdufcb.gdufapi.Pojo.userIndex;
import com.gdufcb.gdufapi.util.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/26 2:49
 * @Version V1.0
 **/
@Service
public class userService {

    @Autowired
    private userMapper userMapper;

    //添加首页用户信息
    public void insertUser(userIndex userIndex){
        int count = userMapper.insert(userIndex);
        if(count>0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }


    public Set findUser(String user) {
      Set set = userMapper.findUser(user);
      return set;
    }

    public boolean exitUser(String user, String addUser) {
        if(userMapper.exitUser(user,addUser)!=null){
            return true;
        }
        return false;
    }

    public void deleteUser(userIndex userIndex) {
        int count = userMapper.delete(userIndex);
        if(count>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }
}
