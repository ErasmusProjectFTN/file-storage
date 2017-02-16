package com.ErasmusProject.storage;

import java.io.File;
import java.io.Serializable;

public class FileDescription implements Serializable{
    private String name;
    private long lastModified;

    public FileDescription(File file) {
        this.name = file.getName();
        this.lastModified = file.lastModified();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public FileDescription(String name, long lastModified) {
        this.name = name;
        this.lastModified = lastModified;
    }

    public FileDescription() {
    }
}
