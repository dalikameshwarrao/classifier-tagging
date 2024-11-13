package com.lumen.classifierTagging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lumen.classifierTagging.exception.ClassifierException;
import com.lumen.classifierTagging.service.ClassifierTaggingService;


@RestController
@RequestMapping("/api")
public class ClassifierTaggingController {

    @Autowired
    private ClassifierTaggingService classifierTaggingService;

    @PostMapping("/updateClassifierInfo")
    public ResponseEntity<String> updateClassifierInfo() {
        try {
            String result = classifierTaggingService.updateClassifierEntities();
            if (result.startsWith("Success")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                throw new ClassifierException("Error executing store procedure classifier Entities");
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } 
    }

}