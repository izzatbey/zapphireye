package com.zapphireye.zapphireye.repository;

import com.zapphireye.zapphireye.model.database.scan.Scan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScanRepository extends MongoRepository<Scan, String> {

}
