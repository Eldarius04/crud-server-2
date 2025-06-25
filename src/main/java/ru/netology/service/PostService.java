package ru.netology.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.repository.PostRepository;
import java.io.*;

@Service
public class PostService {
  private final PostRepository repository;
  private final Gson gson = new Gson();

  @Autowired
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