package dev.tanay.productservice.models;

public class Product extends BaseModel{
    private String title;
    private String desc;
    private String image;
    //doubles have precisions issue, should be int. We are using double for FakeStore API
    private double price;

}
