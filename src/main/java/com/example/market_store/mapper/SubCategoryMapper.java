package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestSubCategoryDto;
import com.example.market_store.dto.responseDto.ResponseSubCategoryDto;
import com.example.market_store.entitie.SubCategory;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SubCategoryMapper {
    SubCategory dtoToModel(RequestSubCategoryDto requestSubCategoryDto);
    @Mapping(source = "category", target = "responseCategoryDto")
    ResponseSubCategoryDto modelToDto(SubCategory subCategory);

    default Page<ResponseSubCategoryDto> modelToDtos(Page<SubCategory> subCategoryPage) {
        return subCategoryPage.map(this::modelToDto);
    }
}
