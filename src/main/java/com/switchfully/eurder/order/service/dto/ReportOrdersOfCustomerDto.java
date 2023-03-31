package com.switchfully.eurder.order.service.dto;

import java.util.List;

public class ReportOrdersOfCustomerDto {
    private List<ReportOrderDto> listOfOrderDto;
    private double price;

    public ReportOrdersOfCustomerDto(){
    }

    public ReportOrdersOfCustomerDto(List<ReportOrderDto> listOfOrderDto, double price) {
        this.listOfOrderDto = listOfOrderDto;
        this.price = price;
    }

    public List<ReportOrderDto> getListOfOrderDto() {
        return listOfOrderDto;
    }

    public double getPrice() {
        return price;
    }
}
