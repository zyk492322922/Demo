package com.zyk.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zyk on 2017/10/29.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  {
    private int id;
    private String userName;
    private String realName;
    private String password;
}
