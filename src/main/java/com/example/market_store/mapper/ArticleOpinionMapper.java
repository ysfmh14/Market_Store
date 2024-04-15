package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestArticleOpinionDto;
import com.example.market_store.dto.responseDto.ResponseArticleOpinionDto;
import com.example.market_store.entitie.ArticleOpinion;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ArticleOpinionMapper {
    ArticleOpinion dtoToModel(RequestArticleOpinionDto requestArticleOpinionDto);

    ResponseArticleOpinionDto modelToDto(ArticleOpinion articleOpinion);

    default Page<ResponseArticleOpinionDto> modelToDtos(Page<ArticleOpinion> articleOpinionPage) {
        return articleOpinionPage.map(this::modelToDto);
    }
}
