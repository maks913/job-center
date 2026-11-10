package ua.edu.nuos.backendjobcenter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.nuos.backendjobcenter.data.Education;
import ua.edu.nuos.backendjobcenter.data.LanguageProficiency;
import ua.edu.nuos.backendjobcenter.data.Resume;
import ua.edu.nuos.backendjobcenter.repository.EducationRepository;
import ua.edu.nuos.backendjobcenter.repository.LanguageProficiencyRepository;
import ua.edu.nuos.backendjobcenter.repository.ResumeRepository;
import ua.edu.nuos.backendjobcenter.repository.UserRepository;

@Service
@AllArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final EducationRepository educationRepository;
    private final LanguageProficiencyRepository languageProficiencyRepository;

    public Resume findById(Long id) {
        return resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Transactional
    public void save(Resume resume, Long userId) {
        if (userId != null) {
            var user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            resume.setUser(user);
            resumeRepository.save(resume);
        }
    }

    @Transactional
    public Long deleteById(Long id) {
        var resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        var user = userRepository.findById(resume.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setResume(null);
        resumeRepository.delete(resume);

        return user.getId();
    }


    @Transactional
    public void saveEducation(Education education, Long resumeId) {
        var resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        var newEducation = new Education();
        newEducation.setResume(resume);
        newEducation.setEducationType(education.getEducationType());
        newEducation.setSpecialty(education.getSpecialty());
        newEducation.setNameOfInstitution(education.getNameOfInstitution());
        newEducation.setEndDate(education.getEndDate());

        educationRepository.save(newEducation);
    }

    @Transactional
    public void saveLanguage(LanguageProficiency language, Long resumeId) {
        var resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        var newLanguage = new LanguageProficiency();

        newLanguage.setLanguage(language.getLanguage());
        newLanguage.setProficiency(language.getProficiency());
        newLanguage.setResume(resume);

        languageProficiencyRepository.save(newLanguage);
    }

    @Transactional
    public Long deleteLanguage(Long id) {
        var languageProficiency = languageProficiencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));

        languageProficiencyRepository.deleteById(id);
        return languageProficiency.getResume().getId();
    }

    @Transactional
    public Long deleteEducation(Long id) {
        var education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found"));

        educationRepository.deleteById(id);
        return education.getResume().getId();
    }
}
