package com.switchfully.eurder.order.service;

import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.exceptions.UnauthorizedException;
import com.switchfully.eurder.order.domain.Order;
import com.switchfully.eurder.order.domain.OrderRepository;
import com.switchfully.eurder.order.service.dto.CreateOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public double save(CreateOrderDto createOrderDto, String customerEmail) {
        checkCustomer(customerEmail);

        Order order = orderMapper.fromDto(createOrderDto);
        return orderRepository.save(order);
    }

    public void checkCustomer(String email){
        if (customerRepository.getByEmail(email).isEmpty()){
            throw new UnauthorizedException();
        }
    }
}
