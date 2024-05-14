package com.example.hotrotinhthue.controller;

// AnhMinhChungController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.hotrotinhthue.model.AnhMinhChung;
import com.example.hotrotinhthue.service.AnhMinhChungService;

import java.io.IOException;

@Controller
public class AnhMinhChungController {

    @Autowired
    private AnhMinhChungService anhMinhChungService;

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("toKhaiThueId", Long.valueOf(0));
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam Long toKhaiThueId, @RequestParam("file") MultipartFile file, Model model) {
        try {
            AnhMinhChung newMinhChung = anhMinhChungService.uploadImage(toKhaiThueId, file);
            model.addAttribute("message", "Thêm thành công");
            model.addAttribute("newMinhChung", newMinhChung);
            return "redirect:/nop-thue";
        } catch (IOException e) {
            model.addAttribute("message", "Thêm thất bại");
            e.printStackTrace();
        }
        return "upload";

    }
}

