package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.model.database.Url;
import com.zapphireye.zapphireye.model.request.CreateUrlRequest;
import com.zapphireye.zapphireye.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url insertList(Url url) {
        return urlRepository.save(url);
    }
}
