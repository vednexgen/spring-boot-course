package com.vednexgen.logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoggerExampleController {

    private static final Logger logger = LoggerFactory.getLogger(LoggerExampleController.class);

    @GetMapping("/logger")
    public String getLogger() {
        logger.info("Fetching logger details...");
        logger.error("Logging error for logger...");
        logger.warn("Logging sample for warning message...");
        logger.debug("Debug info for logger processing...");
        return "Employee fetched successfully!";
    }
}
