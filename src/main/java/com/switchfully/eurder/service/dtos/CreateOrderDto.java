package com.switchfully.eurder.service.dtos;

import java.util.List;


public record CreateOrderDto(List<OrderedItemDto> items) {}

