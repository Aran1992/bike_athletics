package com.h5game.repository;

import com.h5game.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @author russel
 */
public interface PlayerRepository extends JpaRepository<Player, String> {
    @Transactional
    @Modifying
    @Query(value="UPDATE Player SET data= :date WHERE username = :username")
    int update_data(@Param("username") String username, @Param("date") String date);

    @Transactional
    @Modifying
    @Query(value="UPDATE Player SET social_data= :date WHERE username = :username")
    int update_social_data(@Param("username") String username, @Param("date") String date);
}