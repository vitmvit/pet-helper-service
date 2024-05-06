package by.vitikova.discovery.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
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
