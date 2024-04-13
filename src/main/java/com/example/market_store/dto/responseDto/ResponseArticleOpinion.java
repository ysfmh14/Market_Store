package com.example.market_store.dto.responseDto;

import com.example.market_store.dto.requestDto.RequestProductDto;
import com.example.market_store.dto.requestDto.RequestUsersDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseArticleOpinion {
    private String opinionCode;
    private String opinion;
    private RequestUsersDto requestUsersDto;
    private RequestProductDto requestProductDto;
}
