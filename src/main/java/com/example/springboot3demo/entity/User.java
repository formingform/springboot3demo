package com.example.springboot3demo.entity;



import lombok.Data;

import java.util.Date;

@Data
public class User {
    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * email地址
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
