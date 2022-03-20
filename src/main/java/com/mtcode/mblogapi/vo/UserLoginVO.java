package com.mtcode.mblogapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
    private Long id;
    private String nickname;
    private String avatar;
    private String role;
}
