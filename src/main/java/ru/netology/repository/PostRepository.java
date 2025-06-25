package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
  private final AtomicLong counter = new AtomicLong(0);
  private final ConcurrentHashMap<Long, Post> storage = new ConcurrentHashMap<>();

  public List<Post> all() {
    return new ArrayList<>(storage.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(storage.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(counter.incrementAndGet());
    }
    storage.put(post.getId(), post);
    return post;
  }

  public void removeById(long id) {
    storage.remove(id);
  }
}