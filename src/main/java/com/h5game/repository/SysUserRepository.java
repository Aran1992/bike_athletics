package com.h5game.repository;

import com.h5game.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author russel
 */
public interface SysUserRepository extends JpaRepository<SysUser, String> {
}
