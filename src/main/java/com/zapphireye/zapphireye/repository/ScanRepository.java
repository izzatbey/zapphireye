package com.zapphireye.zapphireye.repository;

import com.zapphireye.zapphireye.model.database.Scan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ScanRepository extends MongoRepository<Scan, String> {

}
