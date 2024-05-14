package com.example.hotrotinhthue.service;

// AnhMinhChungService.java
import com.example.hotrotinhthue.repository.MinhChungrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.hotrotinhthue.model.AnhMinhChung;
import com.example.hotrotinhthue.model.ToKhaiThue;
import com.example.hotrotinhthue.repository.ToKhaiThueRepo;

import javax.persistence.EntityNotFoundException;

@Service
public class AnhMinhChungService {

    @Autowired
    private ToKhaiThueRepo toKhaiThueRepo;

    @Autowired
    private MinhChungrepo anhMinhChungRepo;

    private final String UPLOAD_DIR = "uploads/";

    @Transactional
    public AnhMinhChung uploadImage(Long toKhaiThueId, MultipartFile file) throws IOException {
        ToKhaiThue toKhaiThue = toKhaiThueRepo.findById(toKhaiThueId)
                .orElseThrow(() -> new EntityNotFoundException("ToKhaiThue with ID " + toKhaiThueId + " not found"));

        // Save the file to the local file system
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        // Create a new AnhMinhChung entity
        AnhMinhChung newMinhChung = AnhMinhChung.builder()
                .tokhaithue(toKhaiThue)
                .imageUrl(fileName)  // Save only the file name in the database
                .build();

        return anhMinhChungRepo.save(newMinhChung);
    }
}

