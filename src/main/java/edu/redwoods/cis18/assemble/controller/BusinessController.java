package edu.redwoods.cis18.assemble.controller;

import edu.redwoods.cis18.assemble.model.Business;
import edu.redwoods.cis18.assemble.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping
    public List<Business> getAllBusinesses() {
        return businessService.getAllBusinesses();
    }

    /*@PostMapping("/addBusiness")
    public Business addBusiness(@RequestBody Business business) {
        return businessService.createBusiness(business);
    }*/
    @PostMapping("/addBusiness")
    public ResponseEntity<Business> addBusiness(@RequestBody Business business) {
        Business savedBusiness = businessService.createBusiness(business);
        return ResponseEntity.ok(savedBusiness);
    }


    @GetMapping("/location")
    public List<Business> getStoresByLocation(@RequestParam String location) {
        return businessService.findStoreByLocation(location);
    }

    @GetMapping("/name")
    public List<Business> getStoreByName(@RequestParam String name) {
        return businessService.findStoreByName(name);
    }

    @GetMapping("/category")
    public List<Business> getStoresByCategory(@RequestParam String category) {
        return businessService.findStoreByCategory(category);
    }

}
