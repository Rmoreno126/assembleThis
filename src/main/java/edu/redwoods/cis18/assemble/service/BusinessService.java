package edu.redwoods.cis18.assemble.service;

import edu.redwoods.cis18.assemble.model.Business;
import edu.redwoods.cis18.assemble.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    @Autowired
    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    public Business createBusiness(Business business) {
        return businessRepository.save(business);
    }

    public List<Business> findStoreByLocation(String location) {
        return businessRepository.findByLocation(location);
    }

    public List<Business> findStoreByName(String name) {
        List<Business> businesses = businessRepository.findAllByName(name);
        if (businesses.isEmpty()) {
            throw new RuntimeException("No businesses found with name: " + name);
        }
        return businesses;
    }

    public List<Business> findStoreByCategory(String category) {
        return businessRepository.findByCategory(category);
    }

    public Optional<Business> getBusinessById(Long id) {
        return Optional.empty();
    }
}
