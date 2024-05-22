package com.example.market_store.service.Impl;

import com.example.market_store.criteria.PaymentCriteria;
import com.example.market_store.dto.StripeChargeDto;
import com.example.market_store.dto.requestDto.RequestInvoiceDto;
import com.example.market_store.dto.requestDto.RequestPaymentDto;
import com.example.market_store.dto.responseDto.ResponsePaymentDto;
import com.example.market_store.entitie.*;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.PaymentMapper;
import com.example.market_store.repositorie.OrderRepo;
import com.example.market_store.repositorie.PaymentRepo;
import com.example.market_store.service.PaymentService;
import com.example.market_store.service.StripeService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepo paymentRepo;
    private OrderRepo orderRepo;
    private StripeService stripeService;
    private PaymentMapper paymentMapper;
    @Override
    public Page<ResponsePaymentDto> findPaymentByCriteria(PaymentCriteria paymentCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Payment> paymentPage = paymentRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (paymentCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),paymentCriteria.getId()));
            }
            if (paymentCriteria.getPaymentMode() != null){
                predicateList.add(criteriaBuilder.equal(root.get("paymentMode"),paymentCriteria.getPaymentCode()));
            }
            if (paymentCriteria.getPaymentCode() != null){
                predicateList.add(criteriaBuilder.equal(root.get("paymentCode"),paymentCriteria.getPaymentCode()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return paymentMapper.modelToDtos(paymentPage);
    }

    @Override
    public ResponsePaymentDto addPayment(RequestPaymentDto requestPaymentDto) {
        String generatedCodePayment = "PM" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Payment paymentToSave = paymentMapper.dtoToModel(requestPaymentDto);
        paymentToSave.setPaymentCode(generatedCodePayment);
        Optional<Order> order = orderRepo.findById(requestPaymentDto.getOrderId());
        paymentToSave.setOrder(order.get());
        Optional<Payment> existingPayment = paymentRepo.findByPaymentCode(paymentToSave.getPaymentCode());
        if (existingPayment.isPresent()) {
            throw new EntityAlreadyExisteException("Payment already exists with id: " + requestPaymentDto.getId());
        }
        if(paymentToSave.getPaymentMode().equals("Credit Card")){
            StripeChargeDto stripeChargeDto = new StripeChargeDto();
            stripeChargeDto.setAmount(paymentToSave.getAmount());
            stripeChargeDto.setStripeToken(requestPaymentDto.getCardToken());
            stripeService.charge(stripeChargeDto);
        }
        Payment savedPayment = paymentRepo.save(paymentToSave);

        return paymentMapper.modelToDto(savedPayment);
    }

    @Override
    public ResponsePaymentDto UpdatePayment(RequestPaymentDto requestPaymentDto) {
        Optional<Payment> existingPayment = paymentRepo.findByPaymentCode(requestPaymentDto.getPaymentCode());
        if (existingPayment.isEmpty()){
            throw new EntityNotFoundException("Payment Not Found   ");
        }
        Payment paymentToUpdate = paymentMapper.dtoToModel(requestPaymentDto);
        paymentToUpdate.setPaymentCode(existingPayment.get().getPaymentCode());
        Payment updatedPayment= paymentRepo.save(paymentToUpdate);
        return paymentMapper.modelToDto(updatedPayment);
    }

    @Override
    public void deletePayment(long id) {
        Optional<Payment> payment = paymentRepo.findById(id);
        if (payment.isEmpty()){
            throw new EntityNotFoundException("Payment Not Found ID :  "+id);
        }
        paymentRepo.deleteById(id);
    }
    public void generatePdf(HttpServletResponse response , String paymentCode)throws DocumentException, IOException {
        Optional<Payment> payment = paymentRepo.findByPaymentCode(paymentCode);
//        System.out.println(payment.get());
        Users users = payment.get().getOrder().getUser();

        Order order = payment.get().getOrder();
        Payment payment1 = payment.get();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(16);
        font.setColor(Color.BLACK);

        com.lowagie.text.Font font1= FontFactory.getFont(FontFactory.HELVETICA);
        font1.setSize(14);
        com.lowagie.text.Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font2.setSize(11);
        Paragraph p1 = new Paragraph("Invoice to : "+users.getFirstName()+" "+users.getLastName()+"                                                                                     Payment code :"+payment1.getPaymentCode(), font2);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p2 = new Paragraph("                                                                                                                              Payment date : "+payment1.getDateTimePayment().getDayOfMonth()+"-"+payment1.getDateTimePayment().getMonthValue()+"-"+payment1.getDateTimePayment().getYear(), font2);
        p2.setAlignment(Paragraph.ALIGN_LEFT);


        document.add(p1);
        document.add(p2);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f,1.5f,1.5f,1.5f});
        table.setSpacingBefore(60);
        table.setSpacingAfter(40);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);
        FontFactory.getFont(FontFactory.HELVETICA);
        font2.setColor(Color.BLACK);
//Remplire les tablehead
        cell.setPhrase(new Phrase("Items", font2));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price", font2));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantity", font2));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total", font2));
        table.addCell(cell);
        for (OrderDetails orderDetails: order.getOrderDetailsList() ){
            table.addCell(orderDetails.getProductVariant().getProduct().getName());
            table.addCell(orderDetails.getProductVariant().getUnitPrice());
            table.addCell(orderDetails.getQuantity()+"");
            table.addCell(orderDetails.getUnitPrice()*orderDetails.getQuantity()+"");
        }
//Ajouter les info concernons chaque colonne

//        //Ajouter les info concernons chaque colonne
//        table.addCell("T-shirt");
//        table.addCell("500.00 DH");
//        table.addCell("2");
//        table.addCell("1000.00 DH");
//        //Ajouter les info concernons chaque colonne
//        table.addCell("T-shirt");
//        table.addCell("500.00 DH");
//        table.addCell("2");
//        table.addCell("1000.00 DH");
        LineSeparator line = new LineSeparator();
        line.setLineWidth(1); // Épaisseur de la ligne en points
        line.setLineColor(Color.BLACK);
        Paragraph p3 = new Paragraph("                                                                                                                                                                                                                                      Sub Total: "+order.getTotalPrice(), font1);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        p3.setSpacingBefore(10);
        Paragraph p4 = new Paragraph("                                                                                                                                                                                                                                      Tax: 0 dhs", font1);
        p4.setAlignment(Paragraph.ALIGN_LEFT);
        p4.setSpacingAfter(10);
        LineSeparator line2 = new LineSeparator();
        line2.setLineWidth(1);
        line2.setPercentage(29);
        line2.setLineColor(Color.BLACK);
        line2.setAlignment(LineSeparator.ALIGN_RIGHT);
        Paragraph p5 = new Paragraph("                                                                                                                                                                                                                                      Total: "+order.getTotalPrice()+0, font1);
        p5.setAlignment(Paragraph.ALIGN_LEFT);
        p5.setSpacingBefore(3);
        document.add(table);
        document.add(line);
        document.add(p3);
        document.add(p4);
        document.add(line2);
        document.add(p5);

        document.close();
    }
}
