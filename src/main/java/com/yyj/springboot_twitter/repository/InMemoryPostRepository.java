//package com.yyj.springboot_twitter.repository;
//
//import com.yyj.springboot_twitter.repository.post.Post;
//import com.yyj.springboot_twitter.repository.post.PostRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//import java.util.concurrent.atomic.AtomicLong;
//
//
//@Repository
//public class InMemoryPostRepository implements PostRepository {
//
//    private Map<Long, Post> posts = new HashMap<>();
//    private AtomicLong idGenerator = new AtomicLong(1); //먼저 들어온 걸 먼저 처리
//
//    @Override
//    public Post save(Post post) {
//        Long id = post.getId() == null ? idGenerator.getAndIncrement() : post.getId(); //id가 없으면 idGenerator로 만들기
//        post.setId(id);
//        posts.put(id, post);
//        return post;
//    }
//
//    @Override
//    public List<Post> findAll() {
//        return new ArrayList<>(posts.values());
//    }
//
//    @Override
//    public Optional<Post> findById(Long id) {
//        return Optional.ofNullable(posts.get(id));
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        posts.remove(id);
//    }
//
//    @Override
//    public List<Post> findAllPaged(int page, int size) {
//        return posts.values().stream()
//                .sorted((p1, p2) -> Long.compare(p2.getId(), p1.getId()))
//                .skip((long) page * size)
//                .limit(size)
//                .toList();
//    }
//}
