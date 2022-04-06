package com.mtcode.mblogapi.vo;

import com.mtcode.mblogapi.entity.ProfileSetting;
import com.mtcode.mblogapi.entity.ProfileSettingCustom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author TangMingZhang
 * @date 2022/4/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSettingVO extends ProfileSetting {

    List<ProfileSettingCustom> customList;
}
