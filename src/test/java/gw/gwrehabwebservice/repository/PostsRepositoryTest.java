package gw.gwrehabwebservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gw.gwrehabwebservice.domain.Posts;
import gw.gwrehabwebservice.domain.QPosts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static gw.gwrehabwebservice.domain.QPosts.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Transactional
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void 게시글저장_불러오기() throws Exception {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .author("ungk")
                .build();
        postsRepository.save(posts);
//        em.flush();
//        em.clear();
        // when
        List<Posts> postsList = postsRepository.findAll();
        //querydsl
/*
        List<Posts> postsList = queryFactory
                .selectFrom(QPosts.posts)
                .fetch();
*/
        // then
        Posts posts1 = postsList.get(0);
        assertThat(posts1.getTitle()).isEqualTo(title);
        assertThat(posts1.getContent()).isEqualTo(content);
    }

}