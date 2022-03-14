package com.zapphireye.zapphireye.repository;

import com.zapphireye.zapphireye.model.database.Spider;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpiderRepository extends MongoRepository<Spider, String> {
}
