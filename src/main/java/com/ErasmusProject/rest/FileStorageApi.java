package com.ErasmusProject.rest;

import com.ErasmusProject.storage.PDFStorage;
import com.ErasmusProject.storage.UserFiles;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/storage")
public class FileStorageApi {

    @Autowired
    private PDFStorage storage;

    @RequestMapping("/ok")
    public String ok() {
        return "OK";
    }

    @RequestMapping(path = "/user", method = RequestMethod.PUT)
    public ResponseEntity initialize(@RequestParam(name = "userid", required = true) String userid) {

        storage.initialize(userid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/user/pdf/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserFiles listUserPdfs(@PathVariable(name = "userid") String userid) throws IOException {

        return storage.listUserFiles(userid);
    }

    @RequestMapping(path = "/user/pdf/{userid}/zip", method = RequestMethod.GET, produces = "application/zip")
    public void getAllZip(@PathVariable(name = "userid") String userid, HttpServletResponse response) {
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.addHeader("Content-Disposition", "attachment; filename=\"user_pdfs.zip\"");
            ZipOutputStream zos = storage.zip(userid, response.getOutputStream());
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(path = "/user/pdf/{userid}/{fileid}", method = RequestMethod.GET)
    public void getPDF(@PathVariable(name = "userid") String userid, @PathVariable(name = "fileid") String fileid,
            HttpServletResponse response) {

        InputStream myStream = null;
        try {
            myStream = storage.readOne(userid, fileid);

            response.addHeader("Content-disposition", "attachment;filename=" + fileid);
            response.setContentType("application/pdf");

            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(path = "/user/pdf", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity putPDF(@RequestParam(name = "userid") String userid,
            @RequestParam(name = "file", required = true) MultipartFile file) {
    	initialize(userid);
        try {
            storage.write(userid, file.getOriginalFilename(), file);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
