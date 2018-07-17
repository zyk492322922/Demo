package com.zyk.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zyk on 2018/2/10.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductEntity {

    private Long id;
    private String productName;  // 产品名称
    private Double productPrice;        // 产品单价
    private String productSummary;      // 产品简介
    private String productPic;
}
