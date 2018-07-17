package com.zyk.demo.service.product;

import com.zyk.demo.dao.ProductMapper;
import com.zyk.demo.entity.ProductEntity;
import com.zyk.demo.param.ProductParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyk on 2018/2/10.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;
    // 获取产品列表
    @Override
    public List<ProductEntity> getList(ProductParam param) {
        List<ProductEntity> result = new ArrayList<>();
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(param,entity);
        result = productMapper.getList(entity);
        return result;
    }
}
