package com.gdufcb.gdufapi.Pojo;


import lombok.Data;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/19 11:13
 * @Version V1.0
 **/
@Data
public class gdufUser {
    //token
    String token;

    //学生的学号信息
    String studentUser;

    //学生登录教务系统的密码
    String passWord;

    //输入时的学期学年
    String xnxq ;

    //输入对应的周次时间
    String zc;

    //查询空教室对应的日期
    String riqi;

    //查询空教室对应的时间段
    String sjd;

    //查询所用到的方法
    String method;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStudentUser() {
        return studentUser;
    }

    public void setStudentUser(String studentUser) {
        this.studentUser = studentUser;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getXnxq() {
        return xnxq;
    }

    public void setXnxq(String xnxq) {
        this.xnxq = xnxq;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }

    public String getSjd() {
        return sjd;
    }

    public void setSjd(String sjd) {
        this.sjd = sjd;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
