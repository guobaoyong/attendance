package org.wlgzs.attendance.entity;

import lombok.Data;

import javax.persistence.*;


import java.io.Serializable;

/**
 * @author: zsh
 * @Date:21:56 2018/5/4
 * @Description: 用户类
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    /**
     * 用户的id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String userName;
    /**
     * 密码
     */
    @Column(name = "password")
    private String passWord;
    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

}
