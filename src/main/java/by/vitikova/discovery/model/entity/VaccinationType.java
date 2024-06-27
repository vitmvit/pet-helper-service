package by.vitikova.discovery.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Builder
@Entity
@Table(uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "name")
        }
)
public class VaccinationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
