package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.classes.Address;
import com.switchfully.eurder.domain.CustomerRepository;
import com.switchfully.eurder.infrastructure.exceptions.UnauthorizedException;
import com.switchfully.eurder.domain.classes.Item;
import com.switchfully.eurder.domain.ItemRepository;
import com.switchfully.eurder.domain.classes.ItemGroup;
import com.switchfully.eurder.domain.classes.Order;
import com.switchfully.eurder.domain.OrderRepository;
import com.switchfully.eurder.service.dtos.*;
import com.switchfully.eurder.service.mappers.OrderMapper;
import com.switchfully.eurder.infrastructure.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {
//
//
//    private final OrderMapper orderMapper;
//    private final OrderRepository orderRepository;
//    private final ItemRepository itemRepository;
//    private final CustomerRepository customerRepository;
//
//    @Autowired
//    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemRepository itemRepository, CustomerRepository customerRepository) {
//        this.orderMapper = orderMapper;
//        this.orderRepository = orderRepository;
//        this.itemRepository = itemRepository;
//        this.customerRepository = customerRepository;
//    }
//
//    public double save(CreateOrderDto createOrderDto, UUID customerId) {
//        checkCustomer(customerId);
//
//        Order order = createOrderFromCreateOrderDto(createOrderDto, customerId);
//        orderRepository.save(order);
//        return order.getPrice();
//    }
//
//    public Order createOrderFromCreateOrderDto(CreateOrderDto dto, UUID customerId){
//        List<ItemGroup> listOfItemGroup = dto
//                                            .listOfItemsGroup()
//                                            .stream()
//                                            .map(this::fromItemGroupDto)
//                                            .toList() ;
//        return new Order(listOfItemGroup, customerId);
//    }
//
//    public ItemGroup fromItemGroupDto(ItemGroupDto dto){
//        UUID itemId = dto.itemId();
//        Item item = itemRepository.getById(itemId);
//        return new ItemGroup(item, dto.orderedAmount());
//    }
//
//    public ReportOrdersOfCustomerDto getByCustomerId(int customerId){
//        checkCustomer(customerId);
//        List<Order> customerOrders = orderRepository.getByCustomerId(customerId);
//        List<ReportOrderDto> reports = createOrdersReports(customerOrders);
//        double ordersPrice = getOrdersPrice(customerOrders);
//        return  new ReportOrdersOfCustomerDto(reports, ordersPrice);
//    }
//
//    public Double getOrdersPrice(List<Order> orders){
//        return orders
//                .stream()
//                .map(Order::getPrice)
//                .reduce(0.0, Double::sum);
//    }
//
//    public List<ReportOrderDto> createOrdersReports(List<Order> orders){
//        return orders
//                .stream()
//                .map( orderMapper::toDto)
//                .toList();
//    }
//
//    public List<OrderTodayDto> getTodayOrders(String adminId) {
//        Utils.adminAccess(adminId);
//
//        List<Order> todayOrders = orderRepository.getOrdersWithTodayDelivery();
//        return todayOrders
//                .stream()
//                .map(this::toTodayDto)
//                .toList();
//    }
//
//    public OrderTodayDto toTodayDto(Order order){
//        List<ItemGroup> todayItemGroups = getTodayItemGroups(order.getItems());
//        Address address = customerRepository
//                            .getById(order.getCustomerId())
//                            .get()
//                            .getAddress();
//
//        return new OrderTodayDto(order.getOrderId(), todayItemGroups, address);
//
//    }
//
//    private List<ItemGroup> getTodayItemGroups(List<ItemGroup> itemGroupList){
//        return itemGroupList
//                .stream()
//                .filter(itemGroup -> itemGroup.getShippingDate().equals(LocalDate.now()))
//                .toList();
//    }
//
//    public void checkCustomer(UUID customerId){
//        customerRepository
//                .getById(customerId)
//                .orElseThrow(UnauthorizedException::new);
//    }
//
//    public Order getOldOrder(UUID orderId, UUID customerId){
//        checkCustomer(customerId);
//        Order order = orderRepository.getById(orderId);
//        if (!order.getCustomerId().equals(customerId))
//            throw new UnauthorizedException();
//        return order;
//    }
//
//    public ItemGroup updateItemGroup(ItemGroup oldItemGroup){
//        UUID itemId = oldItemGroup.getItem().getId();
//        Item item = itemRepository.getById(itemId);
//        int amount = oldItemGroup.getAmount();
//        return new ItemGroup(item, amount);
//    }
//    public Order updateOldOrder(Order oldOrder){
//        List<ItemGroup> listOfItemGroup = oldOrder
//                .getItems()
//                .stream()
//                .map(this::updateItemGroup)
//                .toList();
//        return new Order(listOfItemGroup, oldOrder.getCustomerId());
//    }
//
//    public double orderAgain(UUID orderId, UUID customerId) {
//        Order oldOrder = getOldOrder(orderId, customerId);
//        Order newOrder = updateOldOrder(oldOrder);
//        return orderRepository.save(newOrder);
//    }
}
