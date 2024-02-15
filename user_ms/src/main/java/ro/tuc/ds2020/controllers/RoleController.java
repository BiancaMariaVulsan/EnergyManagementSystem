package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.RoleDTO;
import ro.tuc.ds2020.services.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping()
    public ResponseEntity<Integer> insertRole(@Valid @RequestBody RoleDTO roleDTO) {
        int roleID = roleService.insert(roleDTO);
        return new ResponseEntity<>(roleID, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RoleDTO>> getRoles() {
        List<RoleDTO> dtos = roleService.findRoles();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRole(@PathVariable int id) {
        try {
            boolean deleted = roleService.delete(id);
            if (deleted) {
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
            } else {
                // Handle the case where the person with the given ID was not found
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle other exceptions if necessary
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
