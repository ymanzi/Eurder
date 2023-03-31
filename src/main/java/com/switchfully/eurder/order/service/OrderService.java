package com.switchfully.eurder.order.service;

import com.switchfully.eurder.customer.domain.Address;
import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.exceptions.UnauthorizedException;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.order.domain.ItemGroup;
import com.switchfully.eurder.order.domain.Order;
import com.switchfully.eurder.order.domain.OrderRepository;
import com.switchfully.eurder.order.service.dto.CreateOrderDto;
import com.switchfully.eurder.order.service.dto.OrderTodayDto;
import com.switchfully.eurder.order.service.dto.ReportOrdersOfCustomerDto;
import com.switchfully.eurder.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemRepository itemRepository, CustomerRepository customerRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
    }

    public double save(CreateOrderDto createOrderDto, UUID customerId) {
        checkCustomer(customerId);

        Order order = orderMapper.fromDto(createOrderDto, customerId);
        return orderRepository.save(order);
    }

    public ReportOrdersOfCustomerDto getByCustomerId(UUID customerId){
        checkCustomer(customerId);
        List<Order> customerOrders = orderRepository.getByCustomerId(customerId);
        return orderMapper.toReportDto(customerOrders);
    }

    public List<OrderTodayDto> getTodayOrders(String adminId) {
        Utils.adminAccess(adminId);

        List<Order> todayOrders = orderRepository.getOrdersWithTodayDelivery();
        return todayOrders
                .stream()
                .map(this::toTodayDto)
                .toList();
    }

    public OrderTodayDto toTodayDto(Order order){
        List<ItemGroup> todayItemGroups = getTodayItemGroups(order.getListOfItemsGroup());
        Address address = customerRepository
                            .getById(order.getCustomerId())
                            .get()
                            .getAddress();

        return new OrderTodayDto(order.getOrderId(), todayItemGroups, address);

    }

    private List<ItemGroup> getTodayItemGroups(List<ItemGroup> itemGroupList){
        return itemGroupList
                .stream()
                .filter(itemGroup -> itemGroup.getShippingDate().equals(LocalDate.now()))
                .toList();
    }

    public void checkCustomer(UUID customerId){
        if (customerRepository.getById(customerId).isEmpty()){
            throw new UnauthorizedException();
        }
    }

    public Order getOldOrder(UUID orderId, UUID customerId){
        checkCustomer(customerId);
        Order order = orderRepository.getById(orderId);
        if (!order.getCustomerId().equals(customerId))
            throw new UnauthorizedException();
        return order;
    }

    public ItemGroup updateItemGroup(ItemGroup oldItemGroup){
        UUID itemId = oldItemGroup.getItem().getId();
        Item item = itemRepository.getById(itemId);
        int amount = oldItemGroup.getAmount();
        return new ItemGroup(item, amount);
    }
    public Order updateOldOrder(Order oldOrder){
        List<ItemGroup> listOfItemGroup = oldOrder
                .getListOfItemsGroup()
                .stream()
                .map(this::updateItemGroup)
                .toList();
        return new Order(listOfItemGroup, oldOrder.getCustomerId());
    }

    public double orderAgain(UUID orderId, UUID customerId) {
        Order oldOrder = getOldOrder(orderId, customerId);
        Order newOrder = updateOldOrder(oldOrder);
        return orderRepository.save(newOrder);
    }
}
