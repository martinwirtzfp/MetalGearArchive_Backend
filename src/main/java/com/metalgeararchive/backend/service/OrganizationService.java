package com.metalgeararchive.backend.service;

import com.metalgeararchive.backend.entity.Organization;
import com.metalgeararchive.backend.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    
    @Autowired
    private OrganizationRepository organizationRepository;
    
    public List<Organization> obtenerTodos() {
        return organizationRepository.findAll();
    }
    
    public Optional<Organization> obtenerPorId(Long id) {
        return organizationRepository.findById(id);
    }
    
    public Organization crear(Organization organization) {
        return organizationRepository.save(organization);
    }
    
    public Organization actualizar(Long id, Organization organizationDetails) {
        return organizationRepository.findById(id).map(organization -> {
            organization.setName(organizationDetails.getName());
            organization.setType(organizationDetails.getType());
            organization.setDescription(organizationDetails.getDescription());
            organization.setImageUrl(organizationDetails.getImageUrl());
            organization.setHeadquarters(organizationDetails.getHeadquarters());
            return organizationRepository.save(organization);
        }).orElseThrow(() -> new RuntimeException("Organización no encontrada"));
    }
    
    public void eliminar(Long id) {
        organizationRepository.deleteById(id);
    }
}
