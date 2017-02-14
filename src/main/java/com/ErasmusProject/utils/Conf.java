package com.ErasmusProject.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
@Scope("singleton")
public class Conf {
    private Config config;

    private String storageRoot;
    private String userStorageRoot;
    private String userPdf;

    @PostConstruct
    public void init() {
        config = ConfigFactory.load("application.conf");
        storageRoot = config.getString("root");
        userStorageRoot = config.getString("user_storage_rel");
        userPdf = config.getString("pdf");
    }

    public String getStorageRoot() {
        return storageRoot;
    }

    public String getUserStorageRoot() {
        return storageRoot + File.separator + userStorageRoot;
    }

    public String getUserPdf() {
        return userPdf;
    }

    @Override
    public String toString() {
        return "Conf{" + "storageRoot='" + storageRoot + '\'' + ", userStorageRoot='" + userStorageRoot + '\'' + '}';
    }

}
