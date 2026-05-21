package dev.tanay.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortCriteria {
    private String parameterName;
    private String sortOrder;
}
