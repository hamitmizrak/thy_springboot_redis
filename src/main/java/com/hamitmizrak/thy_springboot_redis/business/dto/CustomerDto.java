package com.hamitmizrak.thy_springboot_redis.business.dto;

import com.hamitmizrak.thy_springboot_redis.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

// LOMBOK
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Builder
// Validation
// Customer(1)- Address(1)
// Customer(1)- Order(N)
public class CustomerDto extends AuditingAwareBaseDto implements Serializable {

    // SERILESTIRME
    public final static Long serialVersionUID = 1L;

    // FIELD

    // ID
    private Long id;

    // NAME
    @NotEmpty(message = "{customer.name.validation.constraints.NotNull.message}")
    private String name;

    // SURNAME
    @NotEmpty(message = "{customer.surname.validation.constraints.NotNull.message}")
    private String surname;

    // DATE
    @Builder.Default
    private Date systemCreatedDate = new Date(System.currentTimeMillis());

    ////////////////////////////////////////////////////////////////////////////////////////////
    // COMPOSITION
    // Customer(1)- Address(1)
    private AddressDto addressDto;

    // COMPOSITION
    // Customer(1)- Order(N)
    private List<OrderDto> orderDtoList;

} //end AddressDto
