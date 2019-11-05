package com.gdufcb.gdufapi.Pojo;

import lombok.Data;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/11/1 20:25
 * @Version V1.0
 **/
@Data
public class JoinOrganization {
    /*
    组织的名称
     */
    private String organization;
    /*
    加入学生的学号
     */
    private String studentId;
    /*
    加入学生的名字
     */
    private String username;
}
