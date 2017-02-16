package com.ErasmusProject.storage;

import com.ErasmusProject.utils.Conf;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
@Scope("prototype")
public class PDFStorageImpl implements PDFStorage {

    @Autowired
    private Conf conf;

    @PostConstruct
    public void init() {
        File rootFile = new File(conf.getUserStorageRoot());
        if (!rootFile.exists())
            rootFile.mkdirs();
    }

    private String makePath(String userid, String pdfName) {
        String beforePDF = conf.getUserStorageRoot() + File.separator + userid + File.separator + conf.getUserPdf();
        if (pdfName == null || pdfName.isEmpty())
            return beforePDF;
        return beforePDF.concat(File.separator + pdfName);
    }

    @Override
    public void write(String userid, String pdfName, MultipartFile file) throws IOException {
        File f = new File(makePath(userid, pdfName));

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));
            stream.write(bytes);
            stream.close();
        } else {

        }

    }

    @Override
    public InputStream readOne(String userPath, String pdfName) throws IOException {
        return new BufferedInputStream(new FileInputStream(new File(makePath(userPath, pdfName))));
    }

    @Override
    public ZipOutputStream zip(String userPath, OutputStream oos) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(oos);

        File[] files = new File(makePath(userPath, "")).listFiles();

        //packing files
        for (File file : files) {
            //new zip entry and copying inputstream with file to zipOutputStream, after all closing streams
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            FileInputStream fileInputStream = new FileInputStream(file);

            IOUtils.copy(fileInputStream, zipOutputStream);

            fileInputStream.close();
            zipOutputStream.closeEntry();
        }

        return zipOutputStream;
    }

    @Override
    public void initialize(String userId) {
        File file = new File(conf.getUserStorageRoot() + File.separator + userId + File.separator + conf.getUserPdf());
        if (!file.exists())
            file.mkdirs();
    }

    @Override
    public UserFiles listUserFiles(String userID) throws IOException {
        File file = new File(makePath(userID, ""));
        if(!file.exists())
            throw new FileNotFoundException("There is no storage for user: "+userID);

        return new UserFiles(userID, file.listFiles());
    }
}
