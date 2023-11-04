package com.learn.ecommerce.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EditProductRequest{
    private MultipartFile[] files;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String thongSoKiThuat;
    @Min(0)
    private long price;
    @Min(0)
    private long oldPrice;
    @Min(0)
    private int warrantyMonths;
    @Min(0)
    private int quantity;
    @NotEmpty
    private String origin;
    @NotEmpty
    private String attributes;
    @Min(1)
    private int branchId;
    @Min(1)
    private int categoryId;
}