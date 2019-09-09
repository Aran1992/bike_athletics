package com.h5game.controller;

import com.h5game.result.GenericResult;
import com.h5game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author russel
 */
@RestController
@RequestMapping("player")
public class PlayerAction {
    @Autowired
    private PlayerService playerService;

    @PostMapping("save_data")
    public GenericResult saveData(HttpServletRequest request, String data) {
        return playerService.saveData(request.getRemoteUser(), data);
    }

    @GetMapping("load_data")
    public GenericResult loadData(HttpServletRequest request) {
        return playerService.loadData(request.getRemoteUser());
    }

    @PostMapping("save_social_data")
    public GenericResult saveSocialData(HttpServletRequest request, String data) {
        return playerService.saveSocialData(request.getRemoteUser(), data);
    }

    @PostMapping("load_social_data")
    public GenericResult loadSocialData(HttpServletRequest request, String name) {
        return playerService.loadSocialData(name);
    }
}
