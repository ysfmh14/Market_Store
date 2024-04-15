package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestCategoryDto;
import com.example.market_store.dto.responseDto.ResponseCategoryDto;
import com.example.market_store.entitie.Category;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CategoryMapper {
    Category dtoToModel(RequestCategoryDto requestCategoryDto);

    ResponseCategoryDto modelToDto(Category category);

    default Page<ResponseCategoryDto> modelToDtos(Page<Category> categoryPage) {
        return categoryPage.map(this::modelToDto);
    }
}
