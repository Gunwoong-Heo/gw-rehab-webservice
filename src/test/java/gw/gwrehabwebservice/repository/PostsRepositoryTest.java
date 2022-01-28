package gw.gwrehabwebservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gw.gwrehabwebservice.domain.Posts;
import gw.gwrehabwebservice.domain.QPosts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static gw.gwrehabwebservice.domain.QPosts.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
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

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
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

    @Test
    public void BaseTimeEntity_등록() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.of(2022, 1, 29, 1, 25, 0);
        Posts posts = Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build();
        postsRepository.save(posts);

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts1 = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>> createDate = " + posts1.getCreatedDate()
                + ", modifiedDate = " + posts1.getLastModifiedDate()
        );

        assertThat(posts.getCreatedDate().isAfter(now));
        assertThat(posts.getLastModifiedDate().isAfter(now));
    }

    @Test
    public void findAllDesc_Querydsl() throws Exception {
        // given
        Posts posts1 = Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build();
        Posts posts2 = Posts.builder()
                .title("title2")
                .content("content2")
                .author("author2")
                .build();
        postsRepository.save(posts1);
        postsRepository.save(posts2);

        // when
        List<Posts> PostsLists = postsRepository.findAllDesc();

        // then
        assertThat(PostsLists.size()).isEqualTo(2);
        assertThat(PostsLists.get(0).getTitle()).isEqualTo("title2");
        assertThat(PostsLists.get(1).getTitle()).isEqualTo("title");
    }

}