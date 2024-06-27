package by.vitikova.discovery.model.entity;

import by.vitikova.discovery.listener.NotificationListener;
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
@Entity
@Audited
@EntityListeners(NotificationListener.class)
public class Notification extends LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userLogin;
    private String name;
    private String description;
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notification", cascade = CascadeType.ALL)
    private List<NotificationTime> listNotificationTime;
}
