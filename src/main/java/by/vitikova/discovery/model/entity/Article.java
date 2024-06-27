package by.vitikova.discovery.model.entity;

import by.vitikova.discovery.constant.ArticleStatus;
import by.vitikova.discovery.model.entity.parent.LogModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
public class Article extends LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String redactorName;

    private String photoUuid;

    @Column(columnDefinition="text", length=10485760)
    private String content;

    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.ALL)
    private List<Tag> tagList;
}
