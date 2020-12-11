package com.smile.demo.apihandler.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * token信息
 * @author smile
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class TokenInfo {

    @NonNull
    private String token;
    @NonNull
    private Long expireAt;
}
