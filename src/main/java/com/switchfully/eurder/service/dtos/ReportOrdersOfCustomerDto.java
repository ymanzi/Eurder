package com.switchfully.eurder.service.dtos;

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
