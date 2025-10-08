package dev.tanay.productservice.repositories;

public interface CustomQueries {
    String FIND_ALL_BY_TITLE = "select p from Product p where p.title = :name";
}
