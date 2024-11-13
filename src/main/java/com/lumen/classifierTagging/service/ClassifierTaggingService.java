package com.lumen.classifierTagging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lumen.classifierTagging.exception.ClassifierException;
import com.lumen.classifierTagging.repository.ClassifierTaggingRepository;

@Service
public class ClassifierTaggingService {

	@Autowired
	ClassifierTaggingRepository classifierTaggingRepository;


	@Scheduled(fixedRateString = "${scheduler.fixedRate}")
	public String updateClassifierEntities() {
		try {
			classifierTaggingRepository.callUpdateClassifierEntities();
			return "Success: Procedure executed successfully.";
		} catch (Exception e) {
	        throw new ClassifierException("Error executing store procedure classifier Entities");
	    }
	}

}
