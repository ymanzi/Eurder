package com.switchfully.eurder.order.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.switchfully.eurder.order.domain.ItemGroup;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public record CreateOrderDto(List<ItemGroupDto> listOfItemsGroup) {}

