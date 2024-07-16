package by.vitikova.discovery.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Setter
@Getter
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
public class Pedigree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Long recordId;

    @Column(nullable = true)
    private Long parentExistOneId;

    @Column(nullable = true)
    private Long parentExistTwoId;

    @Column(nullable = true)
    private Long parentNotExistOneId;

    @Column(nullable = true)
    private Long parentNotExistTwoId;
}
