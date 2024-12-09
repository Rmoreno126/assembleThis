package edu.redwoods.cis18.assemble.service;

import edu.redwoods.cis18.assemble.model.OperatingHours;
import edu.redwoods.cis18.assemble.repository.OperatingHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperatingHoursService {

    @Autowired
    private OperatingHoursRepository operatingHoursRepository;

    // Create or Update Operating Hours
    public OperatingHours saveOrUpdateOperatingHours(OperatingHours operatingHours) {
        return operatingHoursRepository.save(operatingHours);
    }

    // Get All Operating Hours for a Business
    public List<OperatingHours> getOperatingHoursByBusinessId(Long businessId) {
        return operatingHoursRepository.findAll()
                .stream()
                .filter(hours -> hours.getBusiness().getId().equals(businessId))
                .toList();
    }

    // Get Operating Hours by ID
    public Optional<OperatingHours> getOperatingHoursById(Long id) {
        return operatingHoursRepository.findById(id);
    }

    // Delete Operating Hours by ID
    public void deleteOperatingHours(Long id) {
        operatingHoursRepository.deleteById(id);
    }

}
