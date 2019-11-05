package com.gdufcb.gdufapi.Service;

import com.gdufcb.gdufapi.Mapper.organizationMapper;
import com.gdufcb.gdufapi.Pojo.JoinOrganization;
import com.gdufcb.gdufapi.Pojo.organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/11/1 16:37
 * @Version V1.0
 **/
@Service
public class organizationService {

    @Autowired
    private organizationMapper organizationMapper;

    /*
    添加用户对应的组织信息
     */
    public int insertOrganization(organization organization){
        int i = organizationMapper.insertOrganization(organization);
        if(i>0){
            System.out.println("添加成功");
        }
        return i;
    }


    public String  ifOrganization(String organizationName) {
        System.out.println(organizationName);
        String count = organizationMapper.ifOrganization(organizationName);
        System.out.println(count);
        return count;
    }

    public Set find(String organization) {
        Set<organization> list = organizationMapper.find(organization);
        return list;
    }

    public List findMyOrganization(String studentId) {
        System.out.println(studentId);
        List<organization> myOrganization = organizationMapper.findMyOrganization(studentId);
        return  myOrganization;
    }

    public int joinOrganization(JoinOrganization joinOrganization) {
        int i = organizationMapper.joinOrganization(joinOrganization);
        return i;
    }

    public String ifexist(JoinOrganization joinOrganization) {
        String i = organizationMapper.ifexist(joinOrganization);
        System.out.println(joinOrganization.toString());
        System.out.println(i);
        return i;
    }

    public List findAllUser(String organizationName) {
        List<JoinOrganization> allUser = organizationMapper.findAllUser(organizationName);
        return allUser;
    }
}
