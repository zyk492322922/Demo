package com.zyk.demo.service.product;

import com.zyk.demo.entity.ProductEntity;
import com.zyk.demo.param.ProductParam;

import java.util.List;

/**
 * Created by zyk on 2018/2/10.
 * 产品service
 */

public interface ProductService {

    // 获取产品列表
    List<ProductEntity> getList(ProductParam param);
}
