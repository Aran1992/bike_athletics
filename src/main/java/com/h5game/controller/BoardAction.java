package com.h5game.controller;

import com.h5game.model.FarestMileageBoard;
import com.h5game.model.ScoreBoard;
import com.h5game.model.TotalMileageBoard;
import com.h5game.result.GenericResult;
import com.h5game.service.BoardService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author qss
 * @date 2018/12/13
 */
@RestController
@RequestMapping("board")
public class BoardAction {
    @Autowired
    private BoardService boardService;

    @PostMapping("update_total_mileage")
    public GenericResult updateTotalMileageBoard(HttpServletRequest request,long value) {
        TotalMileageBoard board = new TotalMileageBoard();
        board.setValue(value);
        board.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return boardService.updateTotalMileageBoard(board);
    }

    @GetMapping("get_total_mileage_board")
    public GenericResult getTotalMileageBoard() {
        return boardService.getTotalMileageBoard();
    }

    @PostMapping("update_farest_mileage")
    public GenericResult updateFarestMileageBoard(HttpServletRequest request,long value) {
        FarestMileageBoard board = new FarestMileageBoard();
        board.setValue(value);
        board.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return boardService.updateFarestMileageBoard(board);
    }

    @GetMapping("get_farest_mileage_board")
    public GenericResult getFarestMileageBoard() {
        return boardService.getFarestMileageBoard();
    }

    @PostMapping("update_score")
    public GenericResult updateScoreBoard(HttpServletRequest request,long value) {
        ScoreBoard board = new ScoreBoard();
        board.setValue(value);
        board.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return boardService.updateScoreBoard(board);
    }

    @GetMapping("get_score_board")
    public GenericResult getScoreBoard() {
        return boardService.getScoreBoard();
    }
}
