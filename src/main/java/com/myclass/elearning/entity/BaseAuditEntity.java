package com.myclass.elearning.entity;

import com.myclass.elearning.converter.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseAuditEntity<U> extends BaseEntity {

    @CreatedBy
    @Column(name = "created_by", updatable = false, nullable = false)
    private U createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private U lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime lastModifiedDate;

    public BaseAuditEntity(Long id) {
        super(id);
    }

    public BaseAuditEntity() {

    }
}
