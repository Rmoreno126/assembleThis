package edu.redwoods.cis18.assemble.controller;

import edu.redwoods.cis18.assemble.model.Business;
import edu.redwoods.cis18.assemble.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping
    public List<Business> getAllBusinesses() {
        return businessService.getAllBusinesses();
    }

    @PostMapping
    public Business createBusiness(@RequestBody Business business) {
        return businessService.createBusiness(business);
    }
}
