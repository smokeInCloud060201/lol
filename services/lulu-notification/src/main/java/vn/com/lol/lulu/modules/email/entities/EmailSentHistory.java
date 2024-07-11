package vn.com.lol.lulu.modules.email.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.lol.common.entities.BaseEntity;
import vn.com.lol.lulu.modules.email.enums.EmailSentStatusType;

import java.time.LocalDateTime;
import java.util.List;

@Table
@Getter
@Setter
@NoArgsConstructor
@Entity
public class EmailSentHistory extends BaseEntity {

    @Column(name = "recipients")
    private String recipients;

    @Column(name = "email_sent_status_type")
    @Enumerated(EnumType.STRING)
    private EmailSentStatusType emailSentStatusType;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "error_message")
    private String errorMessage;

    @ManyToMany
    @JoinTable(name = "email_sent_template",
            joinColumns = @JoinColumn(name = "sent_history_id"),
            inverseJoinColumns = @JoinColumn(name = "template_id"))
    private List<EmailTemplate> emailSendTemplates;
}
