package com.h5game.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author russel
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class SysUser {
    @Id
    private String username;
    private String password;
    private String role;
    @Column(updatable = false)
    private Date createTime;
    @Column(updatable = false)
    private Date updateTime;
}
