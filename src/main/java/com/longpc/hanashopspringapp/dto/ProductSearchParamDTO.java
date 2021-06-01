package com.longpc.hanashopspringapp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductSearchParamDTO implements BaseSearchParamDTO {
    private String id;
    private double priceFrom;
    private double priceTo;
    private String name;
    private String brandId;
    private String categoryId;
    private String status;
    private Date createAtBigger;
    private Date createAtLittler;
}
