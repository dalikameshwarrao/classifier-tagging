package com.lumen.classifierTagging.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler(Logger mockLogger) {
	}

	public void handleRuntimeException(RuntimeException ex) {
        logger.error("Operation failed: {}", ex.getMessage(), ex);
        // Log and propagate the exception if necessary
        throw new RuntimeException("Operation Failed: " + ex.getMessage(), ex);
    }
}
