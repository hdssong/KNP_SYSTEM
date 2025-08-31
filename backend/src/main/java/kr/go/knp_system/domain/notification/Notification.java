package kr.go.knp_system.domain.notification;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.AbstractAuditable;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import kr.go.knp_system.domain.member.entity.KnpMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
public class Notification{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificaion_id")
    private Long id;

    private String content;

    private String url;

    @Column(nullable = false)
    private Boolean isRead;

    @Column(nullable = false)
    private NotificationType notificationType;

    // @JoinColumn(name = "em_idnum")
    // private KnpMember receiver;

    @Builder
    public Notification(KnpMember receiver, NotificationType notificationType, String content, String url,
            Boolean isRead) {
        // this.receiver = receiver;
        this.notificationType = notificationType;
        this.content = content;
        this.url = url;
        this.isRead = isRead;
    }

    public enum NotificationType {
        YATA, REVIEW, CHAT
    }
}
