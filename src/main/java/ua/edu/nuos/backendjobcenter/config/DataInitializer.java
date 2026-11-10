package ua.edu.nuos.backendjobcenter.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.nuos.backendjobcenter.data.*;
import ua.edu.nuos.backendjobcenter.enums.*;
import ua.edu.nuos.backendjobcenter.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final VacancyRepository vacancyRepository;
    private final ResumeRepository resumeRepository;
    private final EducationRepository educationRepository;
    private final LanguageProficiencyRepository languageProficiencyRepository;

    @Override
    @Transactional
    public void run(String... args) {

        // Prevent duplicate initialization
        if (userRepository.count() > 0 ||
                companyRepository.count() > 0 ||
                vacancyRepository.count() > 0) {
            return;
        }

        LocalDate today = LocalDate.now();

        // =====================================================
        // ROLES
        // =====================================================

        Role roleSeeker = new Role();
        roleSeeker.setName(RoleEnum.SEEKER);
        roleSeeker.setDescription("Job seeker role");
        roleSeeker = roleRepository.save(roleSeeker);

        Role roleEmployer = new Role();
        roleEmployer.setName(RoleEnum.EMPLOYER);
        roleEmployer.setDescription("Employer / Company owner role");
        roleEmployer = roleRepository.save(roleEmployer);

        // =====================================================
        // CATEGORIES
        // =====================================================

        Category catIt = createCategory("IT & Software Development", "Software engineering, DevOps, QA, and IT management.");
        Category catMarketing = createCategory("Marketing & Sales", "Digital marketing, SEO, SMM, and sales managers.");
        Category catFinance = createCategory("Finance & Accounting", "Accountants, financial analysts, and banking.");
        Category catDesign = createCategory("Design & UX/UI", "Graphic design, web design, and user experience.");
        Category catEngineering = createCategory("Engineering & Shipbuilding", "Marine engineering, mechanical design, and production.");
        // =====================================================
        // EMPLOYERS
        // =====================================================

        User emp1 = createUser("Іван", "Петренко", "+380501112233", "ivan.employer@gmail.com", LocalDate.of(1985, 4, 12), Set.of(roleEmployer));
        User emp2 = createUser("Олена", "Ковальчук", "+380672223344", "olena.hr@techcorp.ua", LocalDate.of(1990, 8, 25), Set.of(roleEmployer));
        User emp3 = createUser("Михайло", "Шевченко", "+380933334455", "m.shevchenko@shipyard.my.ua", LocalDate.of(1978, 11, 2), Set.of(roleEmployer));
        User emp4 = createUser("Олександр", "Бондаренко", "+380991111111", "oleksandr.bondarenko@gmail.com", LocalDate.of(1987, 5, 14), Set.of(roleEmployer));
        User emp5 = createUser("Наталія", "Мороз", "+380992222222", "natalia.moroz@gmail.com", LocalDate.of(1992, 9, 21), Set.of(roleEmployer));
        User emp6 = createUser("Віктор", "Кравченко", "+380993333333", "viktor.kravchenko@gmail.com", LocalDate.of(1980, 2, 8), Set.of(roleEmployer));
        User emp7 = createUser("Ірина", "Савченко", "+380994444444", "iryna.savchenko@gmail.com", LocalDate.of(1989, 12, 3), Set.of(roleEmployer));

        // =====================================================
        // COMPANIES
        // =====================================================

        Company comp1 = createCompany("Global Tech Solutions", 150, "Leading software development company in Ukraine.", emp1);
        Company comp2 = createCompany("InnoSoft UA", 45, "Innovative web and mobile applications agency.", emp2);
        Company comp3 = createCompany("Mykolaiv Shipbuilding Plant", 1200, "Heavy engineering and maritime construction.", emp3);
        Company comp4 = createCompany("Digital Future", 80, "Digital transformation and software solutions.", emp4);
        Company comp5 = createCompany("Black Sea Logistics", 230, "International logistics and transportation services.", emp5);
        Company comp6 = createCompany("FinTech Innovations", 65, "Financial technology and banking software.", emp6);
        Company comp7 = createCompany("Creative Studio UA", 32, "Creative design, branding and digital experiences.", emp7);

        // =====================================================
        // VACANCIES
        // 25 vacancies with different publication dates
        // =====================================================

        Vacancy vac1 = createVacancy("Java Spring Boot Developer", "Київ (Remote)", "2500.00", "Looking for an experienced backend developer with Spring Cloud and Hibernate.", VacancyType.REMOTE, comp1, catIt, today.minusDays(1));
        Vacancy vac2 = createVacancy("React Frontend Engineer", "Львів", "2000.00", "Build modern dashboards with React and TypeScript.", VacancyType.FULL_TIME, comp1, catIt, today.minusDays(1));
        Vacancy vac3 = createVacancy("Digital Marketing Specialist", "Одеса", "1200.00", "Run PPC campaigns, SEO optimization and social media marketing.", VacancyType.PART_TIME, comp2, catMarketing, today.minusDays(5));
        Vacancy vac4 = createVacancy("UI/UX Designer", "Миколаїв", "1500.00", "Design user interfaces for enterprise web applications.", VacancyType.REMOTE, comp2, catDesign, today.minusDays(2));
        Vacancy vac5 = createVacancy("Marine Design Engineer", "Миколаїв", "1800.00", "CAD design for ship structures and systems.", VacancyType.FULL_TIME, comp3, catEngineering, today.minusDays(10));
        Vacancy vac6 = createVacancy("Junior QA Automation", "Харків (Remote)", "900.00", "Test REST APIs using Java and JUnit.", VacancyType.INTERNSHIP, comp1, catIt, today.minusDays(10));
        Vacancy vac7 = createVacancy("Senior Kotlin Developer", "Київ", "3500.00", "Develop backend services using Kotlin, Spring Boot and PostgreSQL.", VacancyType.REMOTE, comp4, catIt, today.minusDays(10));
        Vacancy vac8 = createVacancy("Python Backend Developer", "Львів", "2800.00", "Build REST APIs using Python, Django and FastAPI.", VacancyType.FULL_TIME, comp1, catIt, today.minusDays(15));
        Vacancy vac9 = createVacancy("DevOps Engineer", "Київ", "3200.00", "Manage CI/CD pipelines, Docker and Kubernetes infrastructure.", VacancyType.REMOTE, comp4, catIt, today.minusDays(20));
        Vacancy vac10 = createVacancy("Junior Java Developer", "Миколаїв", "1000.00", "Entry-level Java development position. Spring knowledge is a plus.", VacancyType.INTERNSHIP, comp2, catIt, today.minusDays(15));
        Vacancy vac11 = createVacancy("SEO Specialist", "Одеса", "1400.00", "Improve organic search rankings and manage SEO campaigns.", VacancyType.FULL_TIME, comp2, catMarketing, today.minusDays(25));
        Vacancy vac12 = createVacancy("Sales Manager", "Київ", "1700.00", "Manage customer relationships and generate new business opportunities.", VacancyType.FULL_TIME, comp5, catMarketing, today.minusDays(25));
        Vacancy vac13 = createVacancy("Financial Analyst", "Київ", "2300.00", "Financial modelling, reporting and business analytics.", VacancyType.FULL_TIME, comp6, catFinance, today.minusDays(30));
        Vacancy vac14 = createVacancy("Accountant", "Миколаїв", "1100.00", "Accounting, financial statements and tax documentation.", VacancyType.PART_TIME, comp6, catFinance, today.minusDays(30));
        Vacancy vac15 = createVacancy("Graphic Designer", "Львів", "1600.00", "Create visual identities, marketing materials and brand assets.", VacancyType.REMOTE, comp7, catDesign, today.minusDays(50));
        Vacancy vac16 = createVacancy("Product Designer", "Київ", "2600.00", "Design user-centered digital products and interactive prototypes.", VacancyType.FULL_TIME, comp7, catDesign, today.minusDays(55));
        Vacancy vac17 = createVacancy("Mechanical Engineer", "Миколаїв", "1900.00", "Design mechanical systems and industrial equipment.", VacancyType.FULL_TIME, comp3, catEngineering, today.minusDays(7));
        Vacancy vac18 = createVacancy("Naval Architect", "Миколаїв", "2700.00", "Develop ship structural designs and engineering documentation.", VacancyType.FULL_TIME, comp3, catEngineering, today.minusDays(23));
        Vacancy vac19 = createVacancy("QA Engineer", "Харків", "1500.00", "Perform manual and automated testing of web applications.", VacancyType.REMOTE, comp1, catIt, today.minusDays(23));
        Vacancy vac20 = createVacancy("Business Analyst", "Київ", "2200.00", "Analyze business requirements and document software specifications.", VacancyType.FULL_TIME, comp4, catFinance, today.minusDays(90));
        Vacancy vac21 = createVacancy("Content Manager", "Одеса", "900.00", "Create and manage website content and social media publications.", VacancyType.PART_TIME, comp7, catMarketing, today.minusDays(25));
        Vacancy vac22 = createVacancy("Mobile Android Developer", "Київ", "2400.00", "Develop Android applications using Kotlin and Jetpack Compose.", VacancyType.REMOTE, comp4, catIt, today.minusDays(110));
        Vacancy vac23 = createVacancy("Logistics Coordinator", "Одеса", "1300.00", "Coordinate transportation routes and international deliveries.", VacancyType.FULL_TIME, comp5, catFinance, today.minusDays(120));
        Vacancy vac24 = createVacancy("Electrical Engineer", "Миколаїв", "2100.00", "Work with electrical systems and industrial automation.", VacancyType.FULL_TIME, comp3, catEngineering, today.minusDays(135));
        Vacancy vac25 = createVacancy("Junior Graphic Designer", "Львів", "800.00", "Assist the design team with branding and marketing materials.", VacancyType.INTERNSHIP, comp7, catDesign, today.minusDays(150));

        // =====================================================
        // JOB SEEKERS
        // =====================================================

        User seeker1 = createUser("Андрій", "Мельник", "+380504445566", "andrii.seeker@gmail.com", LocalDate.of(2001, 3, 15), Set.of(roleSeeker));
        User seeker2 = createUser("Марія", "Бойко", "+380675556677", "mariya.boyko@ukr.net", LocalDate.of(1999, 12, 5), Set.of(roleSeeker));
        User seeker3 = createUser("Дмитро", "Ткаченко", "+380936667788", "dmitro.dev@gmail.com", LocalDate.of(1995, 7, 20), Set.of(roleSeeker));
        User seeker4 = createUser("Катерина", "Лисенко", "+380507778899", "kateryna.design@gmail.com", LocalDate.of(1998, 1, 30), Set.of(roleSeeker));
        User seeker5 = createUser("Сергій", "Коваль", "+380678889900", "serg.engineer@gmail.com", LocalDate.of(1988, 9, 10), Set.of(roleSeeker));
        User seeker6 = createUser("Анастасія", "Романенко", "+380671234567", "anastasia.roman@gmail.com", LocalDate.of(2000, 6, 18), Set.of(roleSeeker));
        User seeker7 = createUser("Максим", "Шевчук", "+380931234567", "maksym.shevchuk@gmail.com", LocalDate.of(1997, 2, 11), Set.of(roleSeeker));
        User seeker8 = createUser("Вікторія", "Гончар", "+380501234567", "viktoria.gonchar@gmail.com", LocalDate.of(2002, 10, 7), Set.of(roleSeeker));
        User seeker9 = createUser("Богдан", "Мороз", "+380991234567", "bohdan.moroz@gmail.com", LocalDate.of(1993, 4, 23), Set.of(roleSeeker));
        User seeker10 = createUser("Юлія", "Клименко", "+380681234567", "yuliia.klymenko@gmail.com", LocalDate.of(1996, 11, 19), Set.of(roleSeeker));

        // =====================================================
        // APPLICATIONS AND SAVED VACANCIES
        // =====================================================

        seeker1.getAppVacancies().addAll(Set.of(vac1, vac7, vac8));
        seeker1.getSavedVacancies().addAll(Set.of(vac10, vac22));

        seeker2.getAppVacancies().addAll(Set.of(vac3, vac11, vac12));
        seeker2.getSavedVacancies().addAll(Set.of(vac15, vac16));

        seeker3.getAppVacancies().addAll(Set.of(vac1, vac6, vac19, vac22));
        seeker3.getSavedVacancies().addAll(Set.of(vac7, vac8, vac9));

        seeker4.getAppVacancies().addAll(Set.of(vac4, vac15, vac16));
        seeker4.getSavedVacancies().addAll(Set.of(vac11, vac21, vac25));

        seeker5.getAppVacancies().addAll(Set.of(vac5, vac17, vac18));
        seeker5.getSavedVacancies().addAll(Set.of(vac20, vac24));

        seeker6.getAppVacancies().addAll(Set.of(vac2, vac15, vac25));
        seeker6.getSavedVacancies().addAll(Set.of(vac4, vac16));

        seeker7.getAppVacancies().addAll(Set.of(vac7, vac9, vac13));
        seeker7.getSavedVacancies().addAll(Set.of(vac1, vac20));

        seeker8.getAppVacancies().addAll(Set.of(vac3, vac11, vac21));
        seeker8.getSavedVacancies().addAll(Set.of(vac12, vac15));

        seeker9.getAppVacancies().addAll(Set.of(vac5, vac17, vac18, vac24));
        seeker9.getSavedVacancies().addAll(Set.of(vac23));

        seeker10.getAppVacancies().addAll(Set.of(vac13, vac14, vac20));
        seeker10.getSavedVacancies().addAll(Set.of(vac12, vac23));

        userRepository.saveAll(List.of(seeker1, seeker2, seeker3, seeker4, seeker5, seeker6, seeker7, seeker8, seeker9, seeker10));

        // =====================================================
        // RESUMES
        // =====================================================

        Resume resume1 = createResume(seeker1, new BigDecimal("2400.00"), "Java Developer", "Eager to build scalable microservices.");
        createEducation(resume1, "НУК ім. Макарова", LocalDate.of(2023, 6, 30), EducationType.BACHELOR, "Software Engineering");
        createLangProf(resume1, Language.UKRAINIAN, Proficiency.FREE);createLangProf(resume1, Language.ENGLISH, Proficiency.INTERMEDIATE);

        Resume resume2 = createResume(seeker2, new BigDecimal("1300.00"), "Marketing Manager", "Experienced in lead generation and SMM.");
        createEducation(resume2, "Одеський національний університет", LocalDate.of(2021, 6, 30), EducationType.BACHELOR, "Marketing");
        createLangProf(resume2, Language.UKRAINIAN, Proficiency.FREE);
        createLangProf(resume2, Language.ENGLISH, Proficiency.PROFICIENCY);

        Resume resume3 = createResume(seeker3, new BigDecimal("2200.00"), "Backend Developer", "Strong knowledge of Spring Framework, Docker and PostgreSQL.");
        createEducation(resume3, "КПІ ім. Сікорського", LocalDate.of(2020, 6, 30), EducationType.BACHELOR, "Computer Science");
        createLangProf(resume3, Language.UKRAINIAN, Proficiency.FREE);
        createLangProf(resume3, Language.ENGLISH, Proficiency.PROFICIENCY);
        createLangProf(resume3, Language.GERMANY, Proficiency.INTERMEDIATE);


        Resume resume4 = createResume(seeker4, new BigDecimal("1600.00"), "UI/UX Designer", "Figma, Adobe XD and User Research expert.");
        createEducation(resume4, "Харківська академія дизайну", LocalDate.of(2022, 6, 30), EducationType.BACHELOR, "Design");
        createLangProf(resume4, Language.UKRAINIAN, Proficiency.FREE);
        createLangProf(resume4, Language.ENGLISH, Proficiency.INTERMEDIATE);


        Resume resume5 = createResume(seeker5, new BigDecimal("1900.00"), "Marine Engineer", "10+ years working with ship mechanics and documentation.");
        createEducation(resume5, "Національний університет кораблебудування (НУК)", LocalDate.of(2011, 6, 30), EducationType.BACHELOR, "Shipbuilding");
        createLangProf(resume5, Language.UKRAINIAN, Proficiency.FREE);
        createLangProf(resume5, Language.ENGLISH, Proficiency.PROFICIENCY);


        Resume resume6 = createResume(seeker6, new BigDecimal("1500.00"), "Frontend Developer", "React, JavaScript, TypeScript, HTML and CSS.");
        createEducation(resume6, "Львівська політехніка", LocalDate.of(2023, 6, 30), EducationType.BACHELOR, "Computer Science");
        createLangProf(resume6, Language.UKRAINIAN, Proficiency.FREE);
        createLangProf(resume6, Language.ENGLISH, Proficiency.INTERMEDIATE);


        Resume resume7 = createResume(seeker7, new BigDecimal("3000.00"), "DevOps Engineer", "Docker, Kubernetes, Linux, AWS and CI/CD.");
        createEducation(resume7, "Київський національний університет", LocalDate.of(2019, 6, 30), EducationType.BACHELOR, "Information Technology");
        createLangProf(resume7, Language.UKRAINIAN, Proficiency.FREE);
        createLangProf(resume7, Language.ENGLISH, Proficiency.PROFICIENCY);


        Resume resume8 = createResume(seeker8, new BigDecimal("1200.00"), "Junior Graphic Designer", "Creative designer with knowledge of Figma and Adobe Photoshop.");
        createEducation(resume8, "Київський університет культури", LocalDate.of(2024, 6, 30), EducationType.BACHELOR, "Graphic Design");
        createLangProf(resume8, Language.UKRAINIAN, Proficiency.FREE);
        createLangProf(resume8, Language.ENGLISH, Proficiency.INTERMEDIATE);


        Resume resume9 = createResume(seeker9, new BigDecimal("2100.00"), "Mechanical Engineer", "Experienced in CAD, mechanical systems and industrial design.");
        createEducation(resume9, "НУК ім. Макарова", LocalDate.of(2016, 6, 30), EducationType.BACHELOR, "Mechanical Engineering");
        createLangProf(resume9, Language.UKRAINIAN, Proficiency.FREE);
        createLangProf(resume9, Language.ENGLISH, Proficiency.PROFICIENCY);
        createLangProf(resume9, Language.GERMANY, Proficiency.INTERMEDIATE);


        Resume resume10 = createResume(seeker10, new BigDecimal("2000.00"), "Financial Analyst", "Financial reporting, Excel, data analysis and budgeting.");
        createEducation(resume10, "Київський національний економічний університет", LocalDate.of(2018, 6, 30), EducationType.BACHELOR, "Finance and Accounting");
        createLangProf(resume10, Language.UKRAINIAN, Proficiency.FREE);
        createLangProf(resume10, Language.ENGLISH, Proficiency.PROFICIENCY);
    }

    // =====================================================
    // HELPER METHODS
    // =====================================================

    private Category createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        return categoryRepository.save(category);
    }

    private Company createCompany(String name, int quantityEmployees, String description, User user) {
        Company company = new Company();
        company.setName(name);
        company.setQuantityEmployees(quantityEmployees);
        company.setDescription(description);
        company.setUser(user);

        return companyRepository.save(company);
    }

    private User createUser(String firstName, String lastName, String phone, String email, LocalDate birthDate, Set<Role> roles) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phone);
        user.setEmail(email);
        user.setBirthDate(birthDate);
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    private Vacancy createVacancy(String title, String city, String salary, String description, VacancyType type, Company company, Category category, LocalDate createdAt) {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle(title);
        vacancy.setCity(city);
        vacancy.setSalary(new BigDecimal(salary));
        vacancy.setDescription(description);
        vacancy.setVacancyType(type);
        vacancy.setCompany(company);
        vacancy.setCategory(category);
        vacancy.setCreatedAt(createdAt);

        return vacancyRepository.save(vacancy);
    }

    private Resume createResume(User user, BigDecimal salary, String position, String description) {
        Resume resume = new Resume();
        resume.setUser(user);
        resume.setDesired_salary(salary);
        resume.setDesired_position(position);
        resume.setDescription(description);

        return resumeRepository.save(resume);
    }

    private void createEducation(Resume resume, String institution, LocalDate endDate, EducationType type, String specialty) {
        Education education = new Education();
        education.setResume(resume);
        education.setNameOfInstitution(institution);
        education.setEndDate(endDate);
        education.setEducationType(type);
        education.setSpecialty(specialty);

        educationRepository.save(education);
    }

    private void createLangProf(Resume resume, Language language, Proficiency proficiency) {
        LanguageProficiency prof = new LanguageProficiency();
        prof.setResume(resume);
        prof.setLanguage(language);
        prof.setProficiency(proficiency);

        languageProficiencyRepository.save(prof);
    }
}