package gw.gwrehabwebservice.controller;

import gw.gwrehabwebservice.config.auth.dto.SessionUser;
import gw.gwrehabwebservice.dto.PostsResponseDto;
import gw.gwrehabwebservice.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            // [`index.mustache` 에서 이름 출력 안되는 case 中 이런 이슈도 있음]
            // 윈도우 환경변수 이슈?  `userName`이 윈도우에서 환경변수로 사용되는 이름이기 때문에 pc의 `userName`인 "lg"로 표시되었다.
            // 문제 해결 방법은 간단하다. `userName` 대신 `name` 등 다른 이름으로 바꿔서 사용하면 된다
            model.addAttribute("name", user.getName());
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