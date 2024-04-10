package com.example.SSH_Init.controller;

import com.example.SSH_Init.dto.BaseResponse;
import com.example.SSH_Init.dto.ErrorCode;
import com.example.SSH_Init.entity.Order;
import com.example.SSH_Init.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // PlaceNewOrder
    // POST: http://localhost:8080/orders
    @PostMapping
    public BaseResponse<Object> add(@RequestBody Order order) {
        // Todo
        // create order
        // create orderItems
        return BaseResponse.success(null, "Add Successfully");
    }

    // GetAllOrders
    // GET: http://localhost:8080/orders/all
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('read')")
    public BaseResponse<List<Order>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("UserDetails" + userDetails);
        return BaseResponse.success(orderService.getAll(), "Get Successfully");
    }

    // GetOrderDetail
    // GET: http://localhost:8080/orders/2
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read')")
    public BaseResponse<Order> findById(@PathVariable Long id) {
        return BaseResponse.success(orderService.findById(id), "Find Successfully");
    }

    // UpdateOrderStatus - Cancel Completed Order
    // UpdateOrderStatus - Cancel Processing  Order
    // PATCH: http://localhost:8080/orders/2/cancel
    @PatchMapping("/{id}/cancel")
    public BaseResponse<Object> cancel(@PathVariable Long id) {
        // Todo
        return BaseResponse.success(null, "Cancel Successfully");
    }

    // UpdateOrderStatus - Complete Cancelled Order
    // UpdateOrderStatus - Complete Processing Order
    // PATCH: http://localhost:8080/orders/2/complete
    @PatchMapping("/{id}/complete")
    public BaseResponse<Object> complete(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return new BaseResponse<>(ErrorCode.NOT_FOUND_ERROR, null);
        }
        order.setOrderStatus("Completed");
        // Todo
        orderService.update(order);
        return BaseResponse.success(null, "Order " + id + " Completed");
    }
}
