package com.toyproject.springbootwebservice.config.auth;

import com.toyproject.springbootwebservice.domain.user.Role;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
            .and()
                .authorizeRequests() // URL별 권한 권한 관리를 설정하는 옵션의 시작점 입니다.
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 권한 관리 대상을 지정하는 옵션입니다. permitAll() 옵션을 통해 전체 열람 권한을 주었음
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())  // 해당 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 함
                .anyRequest().authenticated() // 설정된 값들의 나머지 모든 URL에 모두 인증된 사용자들에게만 허용하게 합니다.
            .and()
                .logout()
                    .logoutSuccessUrl("/") // 로그아웃 성공시 / 주소로 이동
            .and()
                .oauth2Login() // OAuth 2 로그인 기능에 대한 여러 설정의 진입점
                    .userInfoEndpoint() // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                        .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }

}
