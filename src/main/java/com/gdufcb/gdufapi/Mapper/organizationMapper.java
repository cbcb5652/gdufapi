package com.gdufcb.gdufapi.Mapper;

import com.gdufcb.gdufapi.Pojo.JoinOrganization;
import com.gdufcb.gdufapi.Pojo.organization;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/11/1 17:11
 * @Version V1.0
 **/
@Component
public interface organizationMapper {

    /*
    添加组织接口
     */
    int insertOrganization(organization organization);


    String ifOrganization(String organizationName);

    Set<organization> find(String organization);

    List<organization> findMyOrganization(String studentId);

    int joinOrganization(JoinOrganization joinOrganization);

    String ifexist(JoinOrganization joinOrganization);

    List<JoinOrganization> findAllUser(String organizationName);
}
