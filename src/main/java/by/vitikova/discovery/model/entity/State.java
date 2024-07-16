package by.vitikova.discovery.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double value;

    private String description;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "dictionary_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_state_dictionary_id_to_id")
    )
    private StateDictionary dictionary;
}
