package com.switchfully.eurder.order.service.dto;

import java.util.List;

public class ReportOrdersOfCustomerDto {
    private final List<ReportOrderDto> listOfOrderDto;
    private final double price;

    public ReportOrdersOfCustomerDto(List<ReportOrderDto> listOfOrderDto) {
        this.listOfOrderDto = listOfOrderDto;
        this.price = listOfOrderDto
                .stream()
                .map(ReportOrderDto::getPrice)
                .reduce(0.0, Double::sum);
    }

    public List<ReportOrderDto> getListOfOrderDto() {
        return listOfOrderDto;
    }

    public double getPrice() {
        return price;
    }
}
