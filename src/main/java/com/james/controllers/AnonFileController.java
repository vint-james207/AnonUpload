package com.james.controllers;

import com.james.entities.AnonFile;
import com.james.services.AnonFileRepository;
import com.james.utils.PasswordStorage;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by jamesyburr on 6/27/16.
 */
@Controller
public class AnonFileController {
    @Autowired
    AnonFileRepository files;

    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer().start();
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, String comment, HttpServletResponse response, Boolean permfile) throws IOException, PasswordStorage.CannotPerformOperationException {
        File dir = new File("public/files");
        dir.mkdirs();

        if (permfile == null) {

            File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);
            FileOutputStream fos = new FileOutputStream(uploadedFile);
            fos.write(file.getBytes());

            AnonFile anonFile = new AnonFile(file.getOriginalFilename(), uploadedFile.getName(), false, comment);
            anonFile = files.save(anonFile);

            if (files.countByPermfile(false) > 2) {
                AnonFile fileInDB = files.findFirstByPermfileFalseOrderByIdAsc();
                File f = new File("public/files" + fileInDB.getRealFilename());
                f.delete();
                files.delete(fileInDB);
            }
        }
        else {
        File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(uploadedFile);
        fos.write(file.getBytes());

        AnonFile anonFile = new AnonFile(file.getOriginalFilename(), uploadedFile.getName(), true, comment);
        anonFile = files.save(anonFile);
        }


        response.sendRedirect("/");
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String delete() {
        return "redirect:/";
    }
}
