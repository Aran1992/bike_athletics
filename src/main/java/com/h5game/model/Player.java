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
public class Player {
    @Id
    private String username;
    private String data;
    private String socialData;
    @Column(updatable = false)
    private Date createTime;
    @Column(updatable = false)
    private Date updateTime;
}
