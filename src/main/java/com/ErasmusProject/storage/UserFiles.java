package com.ErasmusProject.storage;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserFiles implements Serializable{
    private String userID;
    private List<FileDescription> files;

    public UserFiles() {
        files = new ArrayList<>();
    }

    public UserFiles(String userID, List<FileDescription> files) {
        this.userID = userID;
        this.files = files;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<FileDescription> getFiles() {
        return files;
    }

    public void setFiles(List<FileDescription> files) {
        this.files = files;
    }

    public UserFiles(String userID, File[] files) {
        this.userID = userID;
        this.files = new ArrayList<>();

        for(File file: files){
            this.files.add(new FileDescription((file)));
        }
    }
}
