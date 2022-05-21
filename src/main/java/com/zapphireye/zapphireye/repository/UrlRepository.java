package com.zapphireye.zapphireye.repository;

import com.zapphireye.zapphireye.model.database.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {
}
