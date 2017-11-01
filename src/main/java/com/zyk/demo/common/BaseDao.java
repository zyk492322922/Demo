package com.zyk.demo.common;

import java.io.Serializable;

/**
 * Created by zyk on 2017/11/1.
 */
public interface BaseDao<T,PK extends Serializable> {

    /**
     * 新增实体
     * @param entity
     * @return 影响记录条数
     */
    public abstract int insert(T entity);

    /**
     * 修改一个实体对象（UPDATE一条记录）
     * @param entity 实体对象
     * @return 修改的对象个数，正常情况=1
     */
    public abstract int update(T entity);

    /**
     * 按主键取记录
     * @param primaryKey 主键值
     * @return 记录实体对象，如果没有符合主键条件的记录，则返回null
     */
    public abstract T get(PK primaryKey);
}
