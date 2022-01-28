package gw.gwrehabwebservice.repository;

import gw.gwrehabwebservice.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}