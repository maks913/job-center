package ua.edu.nuos.backendjobcenter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.nuos.backendjobcenter.data.Role;
import ua.edu.nuos.backendjobcenter.repository.RoleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RolesService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}
