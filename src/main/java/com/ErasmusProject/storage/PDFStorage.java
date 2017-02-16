package com.ErasmusProject.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

public interface PDFStorage {

    public void write(String userPath, String pdfName, MultipartFile file) throws IOException;

    public InputStream readOne(String userPath, String pdfName) throws IOException;

    public ZipOutputStream zip(String userPath, OutputStream oos) throws IOException;

    public void initialize(String userId);

    public UserFiles listUserFiles(String userID) throws IOException;

}
