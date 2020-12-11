package com.smile.demo.apihandler.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 安全验证-用户信息
 * @author smile
 */
@Data
@ApiModel(description = "安全验证-用户信息")
public class User {

    @NotBlank(message = "username不能为空")
    @ApiModelProperty(name = "username", value = "用户名", example = "api")
    private String username;
    @NotBlank(message = "password不能为空")
    @ApiModelProperty(name = "password", value = "密码", example = "123456")
    private String password;
}
