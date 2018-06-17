package org.wlgzs.attendance.entity;

import lombok.Data;

/**
 * @author: zsh
 * @Date:8:17 2018/5/12
 * @Description:
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private String url;
    private T data;
}
