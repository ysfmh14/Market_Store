package com.example.market_store.dto.requestDto;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestArticleOpinionDto {
    private Long id;
    private String opinionCode;
    private String opinion;
    private Long userId;
    private Long productId;
}
