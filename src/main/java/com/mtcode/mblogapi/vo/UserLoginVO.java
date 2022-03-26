package com.mtcode.mblogapi.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String nickname;
    private String avatar;
    private String role;
}
