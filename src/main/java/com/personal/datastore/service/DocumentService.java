package com.personal.datastore.service;
import com.personal.datastore.dto.DocumentDTO;
import com.personal.datastore.model.Document;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.repository.DocumentRepository;
import com.personal.datastore.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class DocumentService {
    private static final String UPLOAD_DIR = "C:\\Users\\SG0707512\\git-clones\\datastore\\src\\main\\resources\\uploads\\";
    @Autowired
    private DocumentRepository repository;
    @Autowired
    private FamilyRepository familyRepository;
    public DocumentDTO save(Document doc, MultipartFile file) throws IOException {
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        doc.setFilePath(filePath);
        if (doc.getOwner() != null && doc.getOwner().getId() != null) {
            FamilyMember fullOwner = familyRepository
                    .findById(doc.getOwner().getId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            doc.setOwner(fullOwner);
        }
        return mapToDTO(repository.save(doc));
    }
    public List<DocumentDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + id));
        repository.deleteById(id);
    }
    private DocumentDTO mapToDTO(Document doc) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(doc.getId());
        dto.setTitle(doc.getTitle());
        dto.setType(doc.getType());
        dto.setFilePath(doc.getFilePath());
        dto.setExpiryDate(doc.getExpiryDate());
        return dto;
    }
}
