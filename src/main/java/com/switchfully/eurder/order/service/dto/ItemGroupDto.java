package com.switchfully.eurder.order.service.dto;

import com.switchfully.eurder.item.domain.Item;

import java.time.LocalDate;
import java.util.UUID;

public record ItemGroupDto(UUID itemId, int orderedAmount) {}
