package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.classes.*;
import com.switchfully.eurder.domain.CustomerRepository;
import com.switchfully.eurder.infrastructure.EntityNotFoundException;
import com.switchfully.eurder.infrastructure.exceptions.UnauthorizedException;
import com.switchfully.eurder.domain.ItemRepository;
import com.switchfully.eurder.domain.OrderRepository;
import com.switchfully.eurder.service.dtos.*;
import com.switchfully.eurder.service.mappers.OrderMapper;
import com.switchfully.eurder.infrastructure.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
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

    public double save(CreateOrderDto createOrderDto, int customerId) {
        checkCustomer(customerId);
        Customer customer = customerRepository
                                .findById(customerId)
                                .get();
        Order order = createOrderFromCreateOrderDto(createOrderDto, customer);
        orderRepository.save(order);
        return order.getPrice();
    }

    public Order createOrderFromCreateOrderDto(CreateOrderDto dto, Customer customer){
        List<OrderedItem> items = dto
                                            .items()
                                            .stream()
                                            .map(this::fromOrderedItemDto)
                                            .toList() ;
        return new Order(items, customer);
    }

    public OrderedItem fromOrderedItemDto(OrderedItemDto dto){
        int itemId = dto.itemId();
        Item item = itemRepository.findById(itemId)
                .orElseThrow(()-> new EntityNotFoundException("ordering", Item.class, itemId));
        return new OrderedItem(item, dto.orderedAmount());
    }

    public ReportOrdersOfCustomerDto getByCustomerId(int customerId){
        checkCustomer(customerId);
        Customer customer = customerRepository.findById(customerId).get();
        List<Order> customerOrders = orderRepository.findByCustomer(customer);
        List<ReportOrderDto> reports = createOrdersReports(customerOrders);
        double ordersPrice = getOrdersPrice(customerOrders);
        return  new ReportOrdersOfCustomerDto(reports, ordersPrice);
    }

    public Double getOrdersPrice(List<Order> orders){
        return orders
                .stream()
                .map(Order::getPrice)
                .reduce(0.0, Double::sum);
    }

    public List<ReportOrderDto> createOrdersReports(List<Order> orders){
        return orders
                .stream()
                .map( orderMapper::toDto)
                .toList();
    }

    public List<OrderTodayDto> getTodayOrders(String adminId) {
        Utils.adminAccess(adminId);

        List<Order> ordersWithItemsDeliveredToday = orderRepository
                                                    .findAll()
                                                    .stream()
                                                    .filter(this::containsItemsToDeliverToday)
                                                    .toList();
        return ordersWithItemsDeliveredToday
                .stream()
                .map(this::toTodayDto)
                .toList();
    }

    public OrderTodayDto toTodayDto(Order order){
        List<OrderedItem> todayItems = getTodayOrderedItems(order.getItems());
        Address address = order
                            .getCustomer()
                            .getAddress();

        return new OrderTodayDto(order.getId(), todayItems, address);
    }

    public boolean containsItemsToDeliverToday(Order order){
        return !order
                .getItems()
                .stream()
                .filter(items -> items.getShippingDate().equals(LocalDate.now()))
                .toList()
                .isEmpty();
    }

    private List<OrderedItem> getTodayOrderedItems(List<OrderedItem> items){
        return items
                .stream()
                .filter(orderedItem -> orderedItem.getShippingDate().equals(LocalDate.now()))
                .toList();
    }

    public void checkCustomer(int customerId){
        if (!customerRepository.existsById(customerId))
            throw new UnauthorizedException();
    }

    public Order getOldOrder(int orderId, int customerId){
        checkCustomer(customerId);
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(()-> new EntityNotFoundException("ordering", Order.class, orderId));

        if (order.getCustomer().getId() != customerId)
            throw new UnauthorizedException();
        return order;
    }

    public OrderedItem updateOrderedItem(OrderedItem oldOrderedItem){
        int itemId = oldOrderedItem.getItem_id();
        Item item = itemRepository.getById(itemId);
        int amount = oldOrderedItem.getAmount();
        return new OrderedItem(item, amount);
    }
    public Order updateOldOrder(Order oldOrder){
        List<OrderedItem> listOfItemGroup = oldOrder
                .getItems()
                .stream()
                .map(this::updateOrderedItem)
                .toList();
        return new Order(listOfItemGroup, oldOrder.getCustomer());
    }

    public double orderAgain(int orderId, int customerId) {
        Order oldOrder = getOldOrder(orderId, customerId);
        Order newOrder = updateOldOrder(oldOrder);
        Order order = orderRepository.save(newOrder);
        return order.getPrice();
    }
}
