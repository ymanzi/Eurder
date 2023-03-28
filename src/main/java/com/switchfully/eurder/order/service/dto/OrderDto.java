package com.switchfully.eurder.order.service.dto;

import com.switchfully.eurder.order.domain.ItemGroup;

import java.util.List;
import java.util.UUID;

public record OrderDto(UUID orderId, List<ItemGroup> listOfItemsGroup, UUID customerId) {
}
