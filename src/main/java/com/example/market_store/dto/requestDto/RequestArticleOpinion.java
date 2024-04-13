package com.example.market_store.dto.requestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestArticleOpinion {
    private Long id;
    private String opinionCode;
    private String opinion;
    private RequestUsersDto requestUsersDto;
    private RequestProductDto requestProductDto;
}
