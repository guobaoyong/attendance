package org.wlgzs.attendance.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: zsh
 * @Date:16:13 2018/5/5
 * @Description: 学生信息类
 */
@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {
    /**
     * 学生的id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 学生的学号
     */
    @Column(name = "number")
    private String number;
    /**
     * 学生的班级
     */
    @Column(name = "classes")
    private String classes;
    /**
     * 学生的姓名
     */
    @Column(name = "name")
    private String name;

}
