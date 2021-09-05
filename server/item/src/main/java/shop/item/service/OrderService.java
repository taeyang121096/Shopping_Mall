package shop.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.*;
import shop.item.domain.item.Item;
import shop.item.repository.ItemRepository;
import shop.item.repository.MemberRepository;
import shop.item.repository.OrderRepository;
import shop.item.repository.OrderSearch;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        Review review = new Review();
        review.setContent("입력하세요:");

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);


        Order order = Order.createOrder(member, delivery, review, orderItem);

        orderRepository.save(order);

        return order.getId();

    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> searchOrders(OrderSearch orderSearch){
        return orderRepository.findAllByCriteria(orderSearch);
    }


}
