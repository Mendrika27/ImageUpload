package com.ImageUpload.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.StringJoiner;

@RestController
public class ImageController {
    @PostMapping(value = "/lotImage")
    public ResponseEntity<String> updateLotImage(
            @RequestParam("files") MultipartFile[] files,
            RedirectAttributes redirectAttributes, HttpSession session)
            throws IOException {

        StringJoiner sj = new StringJoiner(" , ");
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // next pls
            }
            try {

                byte[] bytes = file.getBytes();
                Properties prop = new Properties();
                String resourceName = "app.properties";
                ClassLoader loader = Thread.currentThread()
                        .getContextClassLoader();
                InputStream resourceStream = loader
                        .getResourceAsStream(resourceName);
                try {
                    prop.load(resourceStream);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                String image_path = prop.getProperty("LOT_DESTINATION_IMG");

                Path path = Paths.get(image_path
                        + file.getOriginalFilename());
                Files.write(path, bytes);

                sj.add(file.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return new ResponseEntity<String>("Success", HttpStatus.OK);

    }
}
