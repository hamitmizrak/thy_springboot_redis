package com.hamitmizrak.thy_springboot_redis.business.dto;

import com.hamitmizrak.thy_springboot_redis.audit.AuditingAwareBaseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter
@Setter

abstract public class BaseDto extends AuditingAwareBaseDto implements Serializable {

    // SERILEŞTIRME
    public static final Long serialVersionUID = 1L;

    // FIELD
    // ID
    protected Long id;

    // CREATED DATE
    protected Date systemCreatedDate;
} //end BaseDto
