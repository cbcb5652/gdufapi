package com.gdufcb.gdufapi.Mapper;

import com.gdufcb.gdufapi.Pojo.userIndex;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/26 2:50
 * @Version V1.0
 **/
@Component
public interface userMapper {

    int insert(userIndex userIndex);

    Set findUser(String user);

    String exitUser(String user, String addUser);

    int delete(userIndex userIndex);
}
