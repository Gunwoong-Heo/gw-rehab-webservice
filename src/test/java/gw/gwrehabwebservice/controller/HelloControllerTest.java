package gw.gwrehabwebservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//- 간단하게 테스트하기 위해서는 @AutoConfigureMockMvc가 아닌 @WebMvcTest를 사용해야 한다.
//- @WebMvcTest는 @SpringBootTest와 같이 사용될 수 없다. 왜냐하면 각자 서로의 MockMvc를 모킹하기 때문에 충돌이 발생하기 때문이다.
//- https://elevatingcodingclub.tistory.com/61
@WebMvcTest
//@SpringBootTest @AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello() throws Exception {

        String hello = "Hello";

        // https://tech.devgd.com/12
        // https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
        mockMvc.perform(get("/hello")
//                        .accept(MediaType.APPLICATION_JSON)
                )
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(hello))
        ;
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        // given
        String name = "Hello";
        int amount = 1000;
        // when
        mockMvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        // then
    }
}