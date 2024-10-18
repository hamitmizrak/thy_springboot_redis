package com.hamitmizrak.thy_springboot_redis.business.dto;

import com.hamitmizrak.thy_springboot_redis.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Builder
// Validation
// Order(N)- Customer(1)
public class OrderDto extends AuditingAwareBaseDto implements Serializable {

    // SERILESTIRME
    public final static Long serialVersionUID = 1L;

    // FIELD

    // ID
    private Long id;

    // NAME
    @NotEmpty(message = "{order.name.validation.constraints.NotNull.message}")
    private String name;

    @NotEmpty(message = "{order.code.validation.constraints.NotNull.message}")
    private String code;

    // DATE
    @Builder.Default
    private Date systemCreatedDate = new Date(System.currentTimeMillis());

    // COMPOSITION
    // Order(N)- Customer(1)
    private CustomerDto customerDto;

    // COMPOSITION
    // PRODUCT

} //end AddressDto
