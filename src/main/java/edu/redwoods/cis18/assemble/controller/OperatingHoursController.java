package edu.redwoods.cis18.assemble.controller;

import edu.redwoods.cis18.assemble.model.OperatingHours;
import edu.redwoods.cis18.assemble.service.OperatingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operating-hours")
public class OperatingHoursController {

    @Autowired
    private OperatingHoursService operatingHoursService;

    // Create or Update Operating Hours
    @PostMapping
    public ResponseEntity<OperatingHours> saveOrUpdateOperatingHours(@RequestBody OperatingHours operatingHours) {
        OperatingHours savedHours = operatingHoursService.saveOrUpdateOperatingHours(operatingHours);
        return ResponseEntity.ok(savedHours);
    }

    // Get All Operating Hours for a Business
    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<OperatingHours>> getOperatingHoursByBusinessId(@PathVariable Long businessId) {
        List<OperatingHours> hours = operatingHoursService.getOperatingHoursByBusinessId(businessId);
        return ResponseEntity.ok(hours);
    }

    // Get Operating Hours by ID
    @GetMapping("/{id}")
    public ResponseEntity<OperatingHours> getOperatingHoursById(@PathVariable Long id) {
        Optional<OperatingHours> hours = operatingHoursService.getOperatingHoursById(id);
        return hours.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Operating Hours by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperatingHours(@PathVariable Long id) {
        operatingHoursService.deleteOperatingHours(id);
        return ResponseEntity.noContent().build();
    }
}
