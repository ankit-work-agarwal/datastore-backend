package com.personal.datastore.controller;

import com.personal.datastore.dto.DocumentDTO;
import com.personal.datastore.model.Document;
import com.personal.datastore.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService service;

    @GetMapping
    public List<DocumentDTO> getAll(@RequestParam(required = false) Long familyMemberId) {
        if (familyMemberId != null) {
            return service.getByFamilyMemberId(familyMemberId);
        }
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DocumentDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Document doc = service.getRawById(id);
        File file = new File(doc.getFilePath());
        Resource resource = new FileSystemResource(file);
        String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }

    @PostMapping("/upload")
    public DocumentDTO upload(
            @RequestPart("document") Document doc,
            @RequestPart("file") MultipartFile file
    ) throws Exception {
        return service.save(doc, file);
    }

    @PutMapping("/{id}")
    public DocumentDTO update(@PathVariable Long id, @RequestBody Document doc) {
        return service.update(id, doc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Document with id " + id + " deleted successfully");
    }
}
