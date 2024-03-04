package dev.tanay.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    private Category category;
    //doubles have precisions issue, should be int. We are using double for FakeStore API
    private double price;

}
