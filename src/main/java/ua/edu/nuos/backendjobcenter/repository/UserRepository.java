package ua.edu.nuos.backendjobcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.edu.nuos.backendjobcenter.data.Role;
import ua.edu.nuos.backendjobcenter.data.User;
import ua.edu.nuos.backendjobcenter.enums.RoleEnum;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRoles(Role roles);

    @Query("""
        select count(v)
        from User u
        join u.appVacancies v
    """)
    Integer countApplications();
}