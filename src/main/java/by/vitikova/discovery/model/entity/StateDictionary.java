package by.vitikova.discovery.model.entity;

import by.vitikova.discovery.listener.StateDictionaryListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
@EntityListeners(StateDictionaryListener.class)
public class StateDictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long recordId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isActive;

    private String uuid;

    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dictionary", cascade = CascadeType.ALL)
    private List<State> listState;
}
