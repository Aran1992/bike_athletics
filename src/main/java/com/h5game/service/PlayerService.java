package com.h5game.service;

import com.h5game.constant.ResultCode;
import com.h5game.model.Player;
import com.h5game.repository.PlayerRepository;
import com.h5game.result.GenericResult;
import com.h5game.result.Results;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author russel
 */
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public GenericResult saveData(String remoteUser, String data) {
        if (StringUtils.isBlank(remoteUser)) {
            return Results.fail(ResultCode.USER_LOGIN_OVERDUE);
        } else if (StringUtils.isBlank(data)) {
            return Results.fail(ResultCode.PARAM_INVALID);
        }
        playerRepository.update_data(remoteUser, data);
        return Results.success();
    }

    public GenericResult loadData(String username) {
        if (StringUtils.isBlank(username)) {
            return Results.fail(ResultCode.USER_LOGIN_OVERDUE);
        }
        Optional<Player> byId = playerRepository.findById(username);
        if (byId.isPresent()) {
            return Results.success("",byId.get().getData());
        }
        return Results.fail();
    }

    public GenericResult saveSocialData(String remoteUser, String data) {
        if (StringUtils.isBlank(remoteUser)) {
            return Results.fail(ResultCode.USER_LOGIN_OVERDUE);
        } else if (StringUtils.isBlank(data)) {
            return Results.fail(ResultCode.PARAM_INVALID);
        }
        playerRepository.update_social_data(remoteUser,data);
        return Results.success();
    }

    public GenericResult loadSocialData(String username) {
        if (StringUtils.isBlank(username)) {
            return Results.fail(ResultCode.USER_LOGIN_OVERDUE);
        }
        Optional<Player> byId = playerRepository.findById(username);
        if (byId.isPresent()) {
            return Results.success("",byId.get().getSocialData());
        }
        return Results.fail();
    }
}
