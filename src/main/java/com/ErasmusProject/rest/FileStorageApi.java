package com.ErasmusProject.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class FileStorageApi {

    @RequestMapping("/ok")
    public String ok() {
        return "OK";
    }
}
