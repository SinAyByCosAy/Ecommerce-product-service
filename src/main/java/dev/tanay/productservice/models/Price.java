package dev.tanay.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Price extends BaseModel{
    @Column(name = "abc")
    private String currency;
    private double price;
}
