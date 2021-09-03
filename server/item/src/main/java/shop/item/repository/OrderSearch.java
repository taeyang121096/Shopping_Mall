package shop.item.repository;

import lombok.Getter;
import lombok.Setter;
import shop.item.domain.OrderStatus;

@Getter@Setter
public class OrderSearch {

    private String userId;
    private OrderStatus orderStatus;
}
