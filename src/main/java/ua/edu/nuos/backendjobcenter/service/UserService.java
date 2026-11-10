package ua.edu.nuos.backendjobcenter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.nuos.backendjobcenter.data.Role;
import ua.edu.nuos.backendjobcenter.data.User;
import ua.edu.nuos.backendjobcenter.data.Vacancy;
import ua.edu.nuos.backendjobcenter.enums.RoleEnum;
import ua.edu.nuos.backendjobcenter.repository.RoleRepository;
import ua.edu.nuos.backendjobcenter.repository.UserRepository;
import ua.edu.nuos.backendjobcenter.repository.VacancyRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VacancyRepository vacancyRepository;
    private final RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(User userUpdate) {

        var user = userRepository.findById(userUpdate.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setId(userUpdate.getId());
        user.setFirstName(userUpdate.getFirstName());
        user.setLastName(userUpdate.getLastName());
        user.setEmail(userUpdate.getEmail());
        user.setBirthDate(userUpdate.getBirthDate());
        user.setPhoneNumber(userUpdate.getPhoneNumber());

        userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findAllEmployers() {
        Role roleEmployer = (Role) roleRepository.findByName(RoleEnum.EMPLOYER)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        return userRepository.findByRoles(roleEmployer);
    }

    @Transactional
    public void saveVacancy(Long vacancyId, Long userId) {
        var user = userRepository.findById(userId);
        var vacancy = vacancyRepository.findById(vacancyId);

        if (user.isPresent() && vacancy.isPresent()) {
            User val = user.get();
            Vacancy vac = vacancy.get();

            if (val.getSavedVacancies().contains(vac)) {
                val.getSavedVacancies().removeIf(v ->
                        v.getId().equals(vacancyId));

            } else {
                val.getSavedVacancies().add(vac);
            }

            userRepository.save(val);
        }

    }

    @Transactional
    public void saveApplication(Long vacancyId, Long userId) {
        var user = userRepository.findById(userId);
        var vacancy = vacancyRepository.findById(vacancyId);

        if (user.isPresent() && vacancy.isPresent()) {
            User val = user.get();
            Vacancy vac = vacancy.get();

            if (val.getAppVacancies().contains(vac)) {
                val.getAppVacancies().removeIf(v ->
                        v.getId().equals(vacancyId));
            } else {
                val.getAppVacancies().add(vac);
            }

            userRepository.save(val);
        }

    }

    public Integer totalApplications() {
       return userRepository.countApplications();
    }
}
