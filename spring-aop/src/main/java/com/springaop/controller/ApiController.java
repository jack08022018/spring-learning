package com.springaop.controller;


import com.springaop.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private TestService testService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/callDaoSuccess")
    public void callDaoSuccess() {
        testService.callDaoSuccess();
    }

    @GetMapping(value = "/callDaoFailed")
    public void callDaoFailed() {
        testService.callDaoFailed();
    }

    @GetMapping(value = "/callDaoTrackTime")
    public void callDaoTrackTime() {
        testService.callDaoTrackTime();
    }

}
