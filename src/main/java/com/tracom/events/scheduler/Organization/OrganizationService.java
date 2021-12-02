package com.tracom.events.scheduler.Organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    /** READ or LIST */
    public List<Organization> listOrganization(){
        return organizationRepository.findAll();


    }
    /** COUNT */
    public int noOfOrg(){
        return organizationRepository.numberOfOrganizations();

    }
    /** CREATE */
    public void saveOrganization(Organization organization){
        Optional<Organization> findByOrganization_name = Optional.ofNullable(organizationRepository.findByOrganization_Name(organization.getOrganization_name()));
        if (findByOrganization_name.isPresent()){
            throw new IllegalStateException("Organization with the same name already exists!");
        }
        organizationRepository.save(organization);
    }
    /** UPDATE */
    public Organization updateOrganization(Integer organization_id){
        return organizationRepository.findById(organization_id).get();
    }
    /** DELETE */
    public void deleteOrganization(Integer organization_id){
        organizationRepository.deleteById(organization_id);
    }

}
