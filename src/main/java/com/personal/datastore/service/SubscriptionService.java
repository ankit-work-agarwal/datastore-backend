package com.personal.datastore.service;

import com.personal.datastore.dto.SubscriptionDTO;
import com.personal.datastore.exception.ResourceNotFoundException;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.model.Subscription;
import com.personal.datastore.repository.FamilyRepository;
import com.personal.datastore.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository repository;

    @Autowired
    private FamilyRepository familyRepository;

    public SubscriptionDTO save(Subscription subscription) {
        resolveOwner(subscription);
        return mapToDTO(repository.save(subscription));
    }

    public List<SubscriptionDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public SubscriptionDTO update(Long id, Subscription updated) {
        Subscription existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id: " + id));
        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setAmount(updated.getAmount());
        existing.setBillingCycle(updated.getBillingCycle());
        existing.setStartDate(updated.getStartDate());
        existing.setRenewalDate(updated.getRenewalDate());
        existing.setIsActive(updated.getIsActive());
        existing.setNotes(updated.getNotes());
        return mapToDTO(repository.save(existing));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id: " + id));
        repository.deleteById(id);
    }

    private void resolveOwner(Subscription subscription) {
        if (subscription.getOwner() != null && subscription.getOwner().getId() != null) {
            FamilyMember owner = familyRepository
                    .findById(subscription.getOwner().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + subscription.getOwner().getId()));
            subscription.setOwner(owner);
        }
    }

    private SubscriptionDTO mapToDTO(Subscription subscription) {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(subscription.getId());
        dto.setName(subscription.getName());
        dto.setCategory(subscription.getCategory());
        dto.setAmount(subscription.getAmount());
        dto.setBillingCycle(subscription.getBillingCycle());
        dto.setStartDate(subscription.getStartDate());
        dto.setRenewalDate(subscription.getRenewalDate());
        dto.setIsActive(subscription.getIsActive());
        dto.setNotes(subscription.getNotes());
        return dto;
    }
}

