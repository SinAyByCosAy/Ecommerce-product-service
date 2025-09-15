package dev.tanay.productservice.models;

import jakarta.persistence.*;

@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
//                   P : C
//    L to R =>      1 : 1
//    R to L =>      m : 1
//    Cardinality => m : 1
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "c_id")
    private Category category;
    //doubles have precisions issue, should be int. We are using double for FakeStore API
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Price price;
}
