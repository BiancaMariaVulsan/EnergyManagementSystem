package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.RoleDTO;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.Role;

public class RoleBuilder {
    public static Role toEntity(RoleDTO roleDTO) {
        return new Role(roleDTO.getId(), roleDTO.getName());
    }

    public static RoleDTO toRoleDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }
}
