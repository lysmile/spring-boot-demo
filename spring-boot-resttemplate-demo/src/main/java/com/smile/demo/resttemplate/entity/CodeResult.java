package com.smile.demo.resttemplate.entity;

import lombok.Data;

/**
 * 接口返回值
 * @author smile
 */
@Data
public class CodeResult {

    private String code;
    private Boolean success;
    private String msg;
    private Object data;
}
