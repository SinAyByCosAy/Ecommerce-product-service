package dev.tanay.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseModel{
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products;
    //this is the same relation being mapped by category attribute in the other(Product) class
}
