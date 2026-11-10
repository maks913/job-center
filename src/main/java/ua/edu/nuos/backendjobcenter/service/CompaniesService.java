package ua.edu.nuos.backendjobcenter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.nuos.backendjobcenter.data.Company;
import ua.edu.nuos.backendjobcenter.enums.RoleEnum;
import ua.edu.nuos.backendjobcenter.repository.CompanyRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CompaniesService {

    private final CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    public void saveCompany(Company company) {
        if (company.getUser().getRoles().stream().findFirst().toString().equalsIgnoreCase(RoleEnum.EMPLOYER.toString())) {
            throw new RuntimeException("Job seeker cannot create company!");
        }
        companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }
}
