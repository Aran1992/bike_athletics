package com.h5game.controller;

import com.h5game.model.SysUser;
import com.h5game.result.GenericResult;
import com.h5game.result.Results;
import com.h5game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author russel
 */
@RestController
@RequestMapping("user")
public class UserAction {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public GenericResult register(SysUser user) {
        return userService.register(user);
    }

    @RequestMapping(value="/err", method=RequestMethod.GET)
    public GenericResult login(HttpServletResponse response, HttpServletRequest request) throws IOException {
        return Results.fail("no login");
    }
}
