package edu.redwoods.cis18.assemble.controller;

import edu.redwoods.cis18.assemble.dto.PaginatedResponse;
import edu.redwoods.cis18.assemble.model.ApiResponse;
import edu.redwoods.cis18.assemble.model.Business;
import edu.redwoods.cis18.assemble.repository.BusinessRepository;
import edu.redwoods.cis18.assemble.service.BusinessService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/business")
public class BusinessController {

    private final BusinessRepository businessRepository;
    private final BusinessService businessService;

    public BusinessController(BusinessRepository businessRepository, BusinessService businessService) {
        this.businessRepository = businessRepository;
        this.businessService = businessService;
    }

    @GetMapping("/paged")
    public PaginatedResponse<Business> getBusinesses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Business> businessPage = businessRepository.findAll(PageRequest.of(page, size));
        return new PaginatedResponse<>(
                businessPage.getContent(),
                businessPage.getNumber(),
                businessPage.getSize(),
                businessPage.getTotalElements(),
                businessPage.getTotalPages()
        );
    }

    @GetMapping("/all")
    public List<Business> getAllBusinesses() {
        return businessService.getAllBusinesses();
    }

    @PostMapping("/addBusiness")
    public ResponseEntity<ApiResponse<Business>> addBusiness(@Valid @RequestBody Business business) {
        Business savedBusiness = businessService.createBusiness(business);
        return ResponseEntity.ok(new ApiResponse<>(true, savedBusiness, "Business added successfully."));
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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getBusinessById(@PathVariable Long id) {
        Optional<Business> business = businessService.getBusinessById(id);
        if (business.isPresent()) {
            Business b = business.get();
            Map<String, Object> response = new HashMap<>();
            response.put("id", b.getId());
            response.put("name", b.getName());
            response.put("location", b.getLocation());
            response.put("category", b.getCategory());
            response.put("latitude", b.getLatitude());
            response.put("longitude", b.getLongitude());
            response.put("operatingHoursSummary", b.getOperatingHoursSummary());
            return ResponseEntity.ok(new ApiResponse<>(true, response, "Business found."));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>(false, null, "Business not found."));
        }
    }
}