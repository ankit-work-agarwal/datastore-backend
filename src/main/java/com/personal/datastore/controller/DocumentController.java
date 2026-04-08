package com.personal.datastore.controller;

import com.personal.datastore.dto.DocumentDTO;
import com.personal.datastore.model.Document;
import com.personal.datastore.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService service;

    @GetMapping
    public List<DocumentDTO> getAll() {
        return service.getAll();
    }

    @PostMapping("/upload")
    public DocumentDTO upload(
            @RequestPart("document") Document doc,
            @RequestPart("file") MultipartFile file
    ) throws Exception {
        return service.save(doc, file);
    }
}