package br.com.softplan.pessoas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@MappedSuperclass
public abstract class BaseModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /* Auditoria */

    @CreatedDate
    @Setter(value = AccessLevel.NONE)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Setter(value = AccessLevel.NONE)
    private Date lastUpdateDate;

    @PrePersist
    private void onCreate() {
        Date currentDate = new Date();
        this.createdAt = currentDate;
        this.lastUpdateDate = currentDate;
    }

    @PreUpdate
    private void onUpdate() {
        this.lastUpdateDate = new Date();
    }
}
