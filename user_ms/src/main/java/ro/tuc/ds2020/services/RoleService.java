package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.RoleDTO;
import ro.tuc.ds2020.dtos.builders.RoleBuilder;
import ro.tuc.ds2020.entities.Role;
import ro.tuc.ds2020.repositories.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public int insert(RoleDTO roleDTO) {
        Role role = RoleBuilder.toEntity(roleDTO);
        role = roleRepository.save(role);
        LOGGER.debug("Role with id {} was inserted in db", role.getId());
        return role.getId();
    }

    public List<RoleDTO> findRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream()
                .map(RoleBuilder::toRoleDTO)
                .collect(Collectors.toList());
    }

    public boolean delete(int id) {
        Role existingRole = roleRepository.findById(id).orElse(null);

        if (existingRole != null) {
            // Delete the existingRole entity from the database
            roleRepository.delete(existingRole);
            return true;
        } else {
            return false; // Role with the specified ID not found
        }
    }
}
