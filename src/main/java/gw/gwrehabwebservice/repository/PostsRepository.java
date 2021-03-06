package gw.gwrehabwebservice.repository;

import gw.gwrehabwebservice.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>, PostsRepositoryCustom {

}