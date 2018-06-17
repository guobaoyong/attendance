package org.wlgzs.attendance.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: zsh
 * @Date:21:26 2018/5/9
 * @Description:
 */
@Data
@Entity
@Table(name = "course")
public class Course {
    /**
     * 课程的id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 课程的名字
     */
    @Column(name = "name")
    private String name;
    /**
     * 课程的开始时间
     */
    @Column(name = "start_time")
    private String start_time;
    /**
     * 课程的结束时间
     */
    @Column(name = "end_time")
    private String end_time;
    /**
     * 课程星期几
     */
    @Column(name = "today")
    private String today;
    /**
     * 上课教室
     */
    @Column(name = "room")
    private String room;
    /**
     * 教室的经度坐标 longitude
     */
    @Column(name = "longitude")
    private String longitude;
    /**
     * dimension 教室的纬度坐标
     */
    @Column(name = "dimension")
    private String dimension;
    /**
     * 上课班级
     */
    @Column(name = "classess")
    private String classess;
}
