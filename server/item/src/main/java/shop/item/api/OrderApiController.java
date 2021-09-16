package shop.item.api;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.item.domain.*;

import shop.item.repository.OrderRepository;
import shop.item.service.MemberService;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;




@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final MemberService memberService;

//    admin 모든 주문 내역 조회 0 && 회원의 주문내역
    @GetMapping("/api/v3/orders/{id}")
    public ResponseOrder ordersV3(@PathVariable("id") Long id,
                                   @RequestParam(value="offset",defaultValue = "0") int offset,
                                   @RequestParam(value="limit",defaultValue = "100") int limit) {
        if(id == 0) {
            List<Order> orders = orderRepository.findAll(offset, limit);
            List<OrderDto> result = orders.stream()
                    .map(o -> new OrderDto(o))
                    .collect(Collectors.toList());
            return new ResponseOrder(result);
        }
        else{
            Member member = memberService.findOne(id);
            List<Order> orders = member.getOrders();
            if(orders.equals(null)){
                return null;
            }
            List<OrderDto> result = orders.stream()
                    .map(o -> new OrderDto(o))
                    .collect(Collectors.toList());
            return new ResponseOrder(result);
        }
    }

    @Data
    static class ResponseOrder{
        private int orderCount;
        private List<OrderDto> allOrder;
        public ResponseOrder(List<OrderDto> orders){
            orderCount = orders.size();
            allOrder = orders;
        }
    }
    @Data
    static class OrderDto {
        private Long orderId;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private int totalCount;
        private int totalPrice;
        private Address address;
        private List<OrderItemDto> orderItems;
        public OrderDto(Order order){
            this.orderId = order.getId();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.totalCount = order.getTotalCount();
            this.totalPrice = order.getTotalPrice();
            this.address = order.getDelivery().getAddress();
            this.orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(Collectors.toList());
        }
    }

    @Data
    static class OrderItemDto {
        private String name;
        private int price;
        private int count;

        public OrderItemDto(OrderItem orderItem){
                this.name = orderItem.getItem().getName();
                this.price = orderItem.getOrderPrice();
                this.count = orderItem.getCount();
        }
    }
}
