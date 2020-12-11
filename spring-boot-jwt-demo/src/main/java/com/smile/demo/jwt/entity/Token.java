package com.smile.demo.jwt.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author yangjunqiang
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Token {

    @NonNull
    private String token;
    @NonNull
    private Long expireAt;
}
