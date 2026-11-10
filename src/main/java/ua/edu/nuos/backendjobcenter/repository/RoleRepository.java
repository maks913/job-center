package ua.edu.nuos.backendjobcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.nuos.backendjobcenter.data.Role;
import ua.edu.nuos.backendjobcenter.enums.RoleEnum;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Object> findByName(RoleEnum name);
}