package com.zapphireye.zapphireye.service;

import com.zapphireye.zapphireye.model.database.Url;
import com.zapphireye.zapphireye.model.request.CreateUrlRequest;

public interface UrlService {
    Url insertList(Url url);
}
