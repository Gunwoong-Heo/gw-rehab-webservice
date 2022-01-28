package gw.gwrehabwebservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gw.gwrehabwebservice.domain.Posts;
import gw.gwrehabwebservice.domain.QPosts;

import javax.persistence.EntityManager;
import java.util.List;

import static gw.gwrehabwebservice.domain.QPosts.*;

public class PostsRepositoryCustomImpl implements PostsRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public PostsRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Posts> findAllDesc() {
        return queryFactory
                .selectFrom(posts)
                .orderBy(posts.id.desc())
                .fetch();
    }
}