package com.mtcode.mblogapi.converter;

import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.vo.BlogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author TangMingZhang
 * @date 2022/3/27
 */
@Mapper
public interface BlogConverter {

    BlogConverter INSTANCE = Mappers.getMapper( BlogConverter.class );

    BlogVO blogToBlogVO(Blog blog);
}
