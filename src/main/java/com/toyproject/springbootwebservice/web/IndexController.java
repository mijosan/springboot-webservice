package com.toyproject.springbootwebservice.web;

import javax.servlet.http.HttpSession;

import com.toyproject.springbootwebservice.config.auth.LoginUser;
import com.toyproject.springbootwebservice.config.auth.dto.SessionUser;
import com.toyproject.springbootwebservice.service.PostsService;
import com.toyproject.springbootwebservice.web.dto.PostsResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    // 앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성했습니다.
    // 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있습니다.
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
