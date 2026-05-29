package dev.tanay.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailMessageDto {
    private Long userId;
    private String name;
    private String email;
}
