package dev.tanay.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
//                   P : C
//    L to R =>      1 : 1
//    R to L =>      m : 1
//    Cardinality => m : 1
    @ManyToOne
    private Category category;
    //doubles have precisions issue, should be int. We are using double for FakeStore API
    private double price;

}
