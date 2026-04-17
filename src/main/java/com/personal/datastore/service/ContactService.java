package com.personal.datastore.service;

import com.personal.datastore.dto.ContactDTO;
import com.personal.datastore.exception.ResourceNotFoundException;
import com.personal.datastore.model.Contact;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.repository.ContactRepository;
import com.personal.datastore.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private FamilyRepository familyRepository;

    public ContactDTO save(Contact contact) {
        if (contact.getAddedBy() != null && contact.getAddedBy().getId() != null) {
            FamilyMember addedBy = familyRepository
                    .findById(contact.getAddedBy().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + contact.getAddedBy().getId()));
            contact.setAddedBy(addedBy);
        }
        return mapToDTO(repository.save(contact));
    }

    public List<ContactDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<ContactDTO> getByFamilyMemberId(Long familyMemberId) {
        return repository.findByAddedBy_Id(familyMemberId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public ContactDTO getById(Long id) {
        Contact contact = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));
        return mapToDTO(contact);
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));
        repository.deleteById(id);
    }

    public ContactDTO update(Long id, Contact updated) {
        Contact existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));
        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setPhone(updated.getPhone());
        existing.setEmail(updated.getEmail());
        existing.setOrganization(updated.getOrganization());
        existing.setNotes(updated.getNotes());
        return mapToDTO(repository.save(existing));
    }

    private ContactDTO mapToDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setCategory(contact.getCategory());
        dto.setPhone(contact.getPhone());
        dto.setEmail(contact.getEmail());
        dto.setOrganization(contact.getOrganization());
        dto.setNotes(contact.getNotes());
        if (contact.getAddedBy() != null) {
            dto.setAddedByName(contact.getAddedBy().getName());
        }
        return dto;
    }
}
