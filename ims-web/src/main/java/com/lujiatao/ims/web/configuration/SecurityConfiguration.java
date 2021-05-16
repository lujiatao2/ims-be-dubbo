package com.lujiatao.ims.web.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lujiatao.ims.api.UserService;
import com.lujiatao.ims.common.entity.User;
import com.lujiatao.ims.common.model.BaseVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用方法级别的授权
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @DubboReference(version = "1.0.0")
    private UserService userService;

    //认证
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            List<User> users = userService.search(username);
            if (users == null || users.size() == 0) {
                throw new UsernameNotFoundException("用户不存在！");
            } else if (users.size() > 1) {
                throw new RuntimeException("存在重名用户！");
            } else {
                User user = users.get(0);
                if (!user.getIsActive()) {
                    throw new DisabledException("用户已禁用！");
                } else {
                    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
                    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
                }
            }
        }).passwordEncoder(new BCryptPasswordEncoder());
    }

    //授权
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").successHandler(new LoginSuccess()).failureHandler(new LoginFailure())
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessHandler(new LogoutSuccess());
        http.exceptionHandling()
                .authenticationEntryPoint(new Handler401())//处理401 Unauthorized
                .accessDeniedHandler(new Handler403());//处理403 Forbidden
        http.csrf().disable();
    }

    private static class LoginSuccess implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(new BaseVO()));
        }

    }

    private static class LoginFailure implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(new BaseVO(1, e.getMessage())));
        }

    }

    private static class LogoutSuccess implements LogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(new BaseVO()));
        }

    }

    private static class Handler401 implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(new BaseVO(1, "未登录！")));
            httpServletResponse.setStatus(401);
        }

    }

    private static class Handler403 implements AccessDeniedHandler {

        @Override
        public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(new BaseVO(1, "没有权限！")));
            httpServletResponse.setStatus(403);
        }

    }

}
