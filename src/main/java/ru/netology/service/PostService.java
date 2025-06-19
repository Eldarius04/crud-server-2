package ru.netology.service;

import com.google.gson.Gson;
import ru.netology.repository.PostRepository;
import ru.netology.model.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class PostService {
  private final PostRepository repository;
  private final Gson gson = new Gson();

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public String all() {
    return gson.toJson(repository.all());
  }

  public String getById(long id) {
    return gson.toJson(repository.getById(id).orElse(null));
  }

  public String save(Reader body) throws IOException {
    try (BufferedReader reader = new BufferedReader(body)) {
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
      var post = gson.fromJson(sb.toString(), Post.class);
      return gson.toJson(repository.save(post));
    }
  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}