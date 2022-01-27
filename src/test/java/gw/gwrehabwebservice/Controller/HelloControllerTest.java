package gw.gwrehabwebservice.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


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
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hello")
//                        .accept(MediaType.APPLICATION_JSON)
                )
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(hello))
        ;
    }
}