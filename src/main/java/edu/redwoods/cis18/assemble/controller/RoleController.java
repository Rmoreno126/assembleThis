package edu.redwoods.cis18.assemble.controller;

import edu.redwoods.cis18.assemble.model.Role;
import edu.redwoods.cis18.assemble.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequest request) {
        Role role = roleService.createRole(request.getName());
        return ResponseEntity.ok(role);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        return roleService.findByName(name) // Corrected method call
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DTO class for request payload
    public static class CreateRoleRequest {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
