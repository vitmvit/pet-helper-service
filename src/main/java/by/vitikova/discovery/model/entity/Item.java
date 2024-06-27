//package by.vitikova.discovery.model.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.envers.Audited;
//
//import java.time.LocalDateTime;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Audited
//@Entity
//public class Item {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private int number;
//
//    private String text;
//
//    private String photoUuid;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "article_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(name = "fk_article_id_to_id")
//    )
//    private Article article;
//}
