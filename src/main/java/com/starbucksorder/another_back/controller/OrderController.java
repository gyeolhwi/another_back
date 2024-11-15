package com.starbucksorder.another_back.controller;

import com.starbucksorder.another_back.aspect.annotation.Log;
import com.starbucksorder.another_back.dto.admin.request.order.ReqAdminOrderCancelDto;
import com.starbucksorder.another_back.dto.admin.request.order.ReqAdminOrderDto;
import com.starbucksorder.another_back.dto.user.request.Order.ReqOrderDto;
import com.starbucksorder.another_back.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 주문목록 저장 ( 적립 안하는 사람은 phoneNumber = "010-" )
    @ApiOperation(value = "주문저장")
    @PostMapping("/order")
    public ResponseEntity<?> add(@RequestBody ReqOrderDto order) {
        return ResponseEntity.ok().body(orderService.saveOrder(order));
    }

    // NOTE: 관리자 주문관리
    @ApiOperation(value = "관리자 주문관리 전체 조회 -> 페이징처리, 기간에 해당하는 조회요청")
    @GetMapping("/admin/order")
    public ResponseEntity<?> getOrders(ReqAdminOrderDto dto) {
        // 조회일자 기본 일주일로 잡아주기
        return ResponseEntity.ok().body(orderService.findByDate(dto));
    }

    @ApiOperation(value = "orderId로 결제 취소에 대한 상태 업데이트 요청")
    @PatchMapping("/admin/order/{orderId}/cancellation")
    public ResponseEntity<?> cancel(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.updateStatus(orderId));
    }

    @ApiOperation(value = "orderId에 대한 주문 상세보기 단 건 조회")
    @GetMapping("/admin/order/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.getOrder(orderId));
    }
}
