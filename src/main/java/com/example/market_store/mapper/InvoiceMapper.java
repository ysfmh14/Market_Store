package com.example.market_store.mapper;

import com.example.market_store.dto.requestDto.RequestInvoiceDto;
import com.example.market_store.dto.requestDto.RequestOrderDetailsDto;
import com.example.market_store.dto.responseDto.ResponseInvoiceDto;
import com.example.market_store.dto.responseDto.ResponseOrderDetailsDto;
import com.example.market_store.entitie.Invoice;
import com.example.market_store.entitie.OrderDetails;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface InvoiceMapper {
    Invoice dtoToModel(RequestInvoiceDto requestInvoiceDto);

    ResponseInvoiceDto modelToDto(Invoice invoice);

    default Page<ResponseInvoiceDto> modelToDtos(Page<Invoice> invoicePage) {
        return invoicePage.map(this::modelToDto);
    }
}
