package com.zyk.demo.dao;

import com.zyk.demo.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zyk on 2018/2/10.
 */
public interface ProductMapper {

    List<ProductEntity> getList(@Param("entity") ProductEntity entity);
}
