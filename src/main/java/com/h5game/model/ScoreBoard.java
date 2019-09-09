package com.h5game.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author qss
 * @date 2018/12/13
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class ScoreBoard {
    @Id
    private String username;
    private long value;
}
