package by.vitikova.discovery.model.entity;

import by.vitikova.discovery.constant.SexName;
import by.vitikova.discovery.listener.RecordListener;
import by.vitikova.discovery.model.entity.parent.LogModel;
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
@EntityListeners(RecordListener.class)
public class Record extends LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userLogin;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String animalType;

    @Column(nullable = true)
    private String breed;

    @Column(nullable = true)
    private String uuidAvatar;

    @Column(nullable = true)
    private LocalDateTime dataBirthday;

    @Column(nullable = true)
    private String fullName;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private SexName sex;

    @Column(nullable = true)
    private String description;

    private boolean hasPedigree;

    private boolean hasExhibition;
}
