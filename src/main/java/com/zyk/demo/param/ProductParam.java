package com.zyk.demo.param;

import lombok.Data;

/**
 * Created by zyk on 2018/2/10.
 */
@Data
public class ProductParam {

    private Long id;
    private String productName;  // 产品名称
    private Double productPrice;        // 产品单价
    private String productSummary;      // 产品简介
}
