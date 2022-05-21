package com.zapphireye.zapphireye.model.controller;

import com.zapphireye.zapphireye.model.database.Url;
import com.zapphireye.zapphireye.model.request.CreateUrlRequest;
import com.zapphireye.zapphireye.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@AllArgsConstructor
public class UrlController {
    @Autowired
    private UrlService urlService;


    @RequestMapping(method = RequestMethod.POST, path = "/urlList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> insertUrl(@RequestBody(required = true) CreateUrlRequest request){
        Url urlModel = new Url();

        String name = request.getName();
        String url = request.getUrl();
        String operator = request.getOperator();
        String period = request.getPeriod();
        String desc = request.getDesc();
        urlModel.setName(name);
        urlModel.setUrl(url);
        urlModel.setOperator(operator);
        urlModel.setPeriod(period);
        urlModel.setDesc(desc);

        try {
            urlService.insertList(urlModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Data Already Created");
        }
        return ResponseEntity.ok("Url Created");
    }
}
