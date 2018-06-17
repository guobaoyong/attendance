package org.wlgzs.attendance.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: zsh
 * @Date:22:11 2018/5/9
 * @Description:
 */
@Data
@Entity
@Table(name = "signin")
public class Signin {
    /**
     * 签到情况id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 签到人姓名
     */
    @Column(name = "name")
    private String name;
    /**
     * 签到班级
     */
    @Column(name = "classes")
    private String classes;
    /**
     * 签到学号
     */
    @Column(name = "number")
    private String number;
    /**
     * 签到时间
     */
    @Column(name = "time")
    private String time;
    /**
     * 签到教室
     */
    @Column(name = "room")
    private String room;
    /**
     * 课程名
     */
    @Column(name = "course_name")
    private String course_name;
    /**
     * 签到IP
     */
    @Column(name = "ip")
    private String ip;

}
