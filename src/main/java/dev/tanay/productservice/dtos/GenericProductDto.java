package dev.tanay.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
