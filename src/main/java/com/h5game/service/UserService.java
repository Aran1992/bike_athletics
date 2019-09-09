package com.h5game.service;

import com.h5game.constant.Constant;
import com.h5game.model.Player;
import com.h5game.model.SysUser;
import com.h5game.repository.PlayerRepository;
import com.h5game.repository.SysUserRepository;
import com.h5game.result.GenericResult;
import com.h5game.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author russel
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Value("${spring.security.user.name}")
    private String admin;
    @Value("${spring.security.user.password}")
    private String password;

    public GenericResult register(SysUser sysUser) {
        if (sysUserRepository.existsById(sysUser.getUsername()) || admin.equals(sysUser.getUsername())) {
            return Results.fail("用户名已被使用");
        }
        sysUser.setPassword(bCryptPasswordEncoder().encode(sysUser.getPassword()));
        sysUserRepository.save(sysUser);

        Player player = new Player();
        player.setUsername(sysUser.getUsername());
        playerRepository.save(player);

        return Results.success("注册成功");
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws AuthenticationException {
        if (StringUtils.isBlank(s)) {
            throw new UsernameNotFoundException("请填写用户名");
        } else if (s.equals(admin)) {
            return new User(admin, password, AuthorityUtils.commaSeparatedStringToAuthorityList(Constant.ROLE_PREFIX.concat("ADMIN")));
        } else {
            Optional<SysUser> byId = sysUserRepository.findById(s);
            if (byId.isPresent()) {
                SysUser sysUser = byId.get();
                return new User(sysUser.getUsername(), sysUser.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(Constant.ROLE_PREFIX.concat(sysUser.getRole())));
            } else {
                throw new UsernameNotFoundException("用户不存在");
            }
        }
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                String msg = "登录失败:" + exception.getMessage();
                logger.info(msg);
                writer(response, Results.fail(msg));
            }
        };
    }

    @Bean
    public SimpleUrlAuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                User userDetails = (User) authentication.getPrincipal();
                String msg = "用户[" + userDetails.getUsername() + "]登陆成功";
                writer(response, Results.success(msg));
                logger.info(msg);
            }
        };
    }

    private void writer(HttpServletResponse response, Object content) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(Constant.GSON.toJson(content));
    }
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                try {
                    User userDetails = (User) authentication.getPrincipal();
                    String msg = "用户[" + userDetails.getUsername() + "]退出登陆";
                    log.info(msg);
                    writer(httpServletResponse, Results.success(msg));
                } catch (NullPointerException e) {
                }
            }
        };
    }
}

