package edu.redwoods.cis18.assemble.service;

import edu.redwoods.cis18.assemble.model.Business;
import edu.redwoods.cis18.assemble.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;


    private List<Business> businesses;


    public List<Business> getAllBusinesses() {
        return List.of();
    }

    public Business createBusiness(Business business) {
        return null;
    }

    public List<Business> findStoreByLocation(String location) {
        return Business.findStoreByLocation(businesses, location);
    }

    public Business findStoreByName(String name) {
        return Business.findStoreByName(businesses, name);
    }

    public List<Business> findStoreByCategory(String category) {
        return Business.findStoreByCategory(businesses, category);
    }

    public Business createBusinessProfile(Business business) {
        return businessRepository.save(business);
    }

}
