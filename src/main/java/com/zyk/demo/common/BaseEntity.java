package com.zyk.demo.common;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zyk on 2017/11/2.
 */
@Data
public class BaseEntity implements Serializable {

    @Id
    protected Long id;
    protected Date createTime;
    protected String createUser;
    protected Date updateTime;
    protected String updateUser;

    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }else if(obj == null){
            return false;
        }else if(this.getClass() != obj.getClass()){
            return false;
        }else{
            BaseEntity other = (BaseEntity) obj;
            return this.id != null && other.getId() != null ? this.id.equals(other.getId()) : false;
        }
    }

    public int hashCode(){
        boolean prime = true;
        byte step = 1;
        int result = 31 * step + (this.id == null ? 0 :this.id.hashCode());
        return result;
    }
}
