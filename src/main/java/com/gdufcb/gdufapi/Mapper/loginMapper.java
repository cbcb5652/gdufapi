package com.gdufcb.gdufapi.Mapper;

import org.springframework.stereotype.Component;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/27 22:12
 * @Version V1.0
 **/
@Component
public interface loginMapper {

    int insertUser(String user);

    int changestate(String user);

    String  ifOnline(String user);
}
