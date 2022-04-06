package com.mtcode.mblogapi.converter;

import com.mtcode.mblogapi.entity.ProfileSetting;
import com.mtcode.mblogapi.vo.ProfileSettingVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author TangMingZhang
 * @date 2022/4/6
 */
@Mapper
public interface ProfileSettingConverter {

    ProfileSettingConverter INSTANCE = Mappers.getMapper(ProfileSettingConverter.class);

    ProfileSettingVO profileSettingToProfileSettingVO(ProfileSetting profileSetting);
}
