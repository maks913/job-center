package ua.edu.nuos.backendjobcenter.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "desired_salary", precision = 10, scale = 2)
    private BigDecimal desired_salary;

    @Column(name = "desired_position")
    private String desired_position;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.REMOVE)
    private Set<Education> educations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.REMOVE)
    private Set<LanguageProficiency> languageProficiencies = new LinkedHashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", desired_salary=" + desired_salary +
                ", desired_position='" + desired_position + '\'' +
                ", description='" + description + '\'' +
                ", educations=" + educations +
                ", languageProficiencies=" + languageProficiencies +
                ", user=" + user +
                '}';
    }
}