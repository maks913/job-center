package ua.edu.nuos.backendjobcenter.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import ua.edu.nuos.backendjobcenter.enums.Language;
import ua.edu.nuos.backendjobcenter.enums.Proficiency;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "language_proficiency")
public class LanguageProficiency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated
    @Column(name = "language")
    private Language language;

    @Enumerated
    @Column(name = "proficiency")
    private Proficiency proficiency;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        LanguageProficiency that = (LanguageProficiency) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}