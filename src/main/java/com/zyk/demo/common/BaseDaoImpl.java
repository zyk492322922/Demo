package com.zyk.demo.common;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by zyk on 2017/11/1.
 */
public class BaseDaoImpl <T, PK extends Serializable> extends
        SqlSessionDaoSupport implements BaseDao<T, PK> {

    private String namespace;

    public static final String SQLID_INSERT = "insert";
    public static final String SQLID_UPDATE = "update";
    public static final String SQLID_SELECT = "select";

    @Resource(name = "sqlSessionTemplate")
    public void setSuperSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public int insert(T entity) {
        int rows = 0;
        try {
            rows = getSqlSession().insert(namespace + "." + SQLID_INSERT,
                    entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int update(T entity) {
        int rows = 0;
        try {
            rows = getSqlSession().update(namespace + "." + SQLID_UPDATE,
                    entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public T get(PK primaryKey) {
        try {
            return getSqlSession().selectOne(namespace + "." + SQLID_SELECT,primaryKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
