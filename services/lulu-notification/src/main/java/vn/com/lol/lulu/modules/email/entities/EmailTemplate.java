package vn.com.lol.lulu.modules.email.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.lol.common.entities.BaseEntity;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class EmailTemplate extends BaseEntity {

    @Column(name = "template_id")
    private String templateId;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;
}
