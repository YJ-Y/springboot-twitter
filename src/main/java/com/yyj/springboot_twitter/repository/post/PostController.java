package com.yyj.springboot_twitter.repository.post;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        Post newPost = Post.builder()
                .content(post.getContent())
                .commentCount(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return postRepository.save(newPost);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다. id=" + id));
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다. id=" + id));

        post.updateContent(postRequest.getContent());
        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }

    @GetMapping("/search")
    public Slice<Post> searchPosts(
            @RequestParam(required = false) Long lastPostId,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(0, size, Sort.by("id").descending());

        if (lastPostId == null) {
            return postRepository.findAllBy(pageable);
        } else {
            return postRepository.findByIdLessThan(lastPostId, pageable);
        }
    }
}