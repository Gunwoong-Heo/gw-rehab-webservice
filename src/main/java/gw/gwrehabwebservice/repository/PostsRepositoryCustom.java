package gw.gwrehabwebservice.repository;

import gw.gwrehabwebservice.domain.Posts;

import java.util.List;

public interface PostsRepositoryCustom {
    List<Posts> findAllDesc();
}