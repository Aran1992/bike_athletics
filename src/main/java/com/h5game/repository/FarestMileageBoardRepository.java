package com.h5game.repository;

import com.h5game.model.FarestMileageBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author qss
 * @date 2018/12/13
 */
public interface FarestMileageBoardRepository extends JpaRepository<FarestMileageBoard, Long> {
    @Query(value = "SELECT `value`,`username` FROM `farest_mileage_board` ORDER BY `value` DESC LIMIT 100", nativeQuery = true)
    List<FarestMileageBoard> init();
}
