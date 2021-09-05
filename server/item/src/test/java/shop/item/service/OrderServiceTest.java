package shop.item.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.Address;
import shop.item.domain.Member;
import shop.item.domain.Order;
import shop.item.domain.OrderStatus;
import shop.item.domain.item.Book;
import shop.item.repository.OrderRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void 상품주문() throws Exception{
//        given
        Address address = new Address("seoul","1234-2","123-123");
        Member member = Member.createMember("lso", "lsolyh", "jiwoo", "jiwoo", "01023232", address);
        em.persist(member);

        Book book = new Book();
        book.setName("jpa");
        book.setStock(20);
        book.setPrice(20000);
        em.persist(book);

        int count = 2;
//        when
        Long orderId = orderService.order(member.getId(), book.getId(), count);
        Order one = orderRepository.findOne(orderId);
//        then
        assertEquals(OrderStatus.ORDER,one.getStatus());
        assertEquals(20000*count,one.getTotalPrice());
        assertEquals(18,book.getStock());
        assertEquals(1,one.getOrderItems().size());

    }
    @Test
    void 주문취소() throws Exception{
        //        given
        Address address = new Address("seoul","1234-2","123-123");
        Member member = Member.createMember("lso", "lsolyh", "jiwoo", "jiwoo", "01023232", address);
        em.persist(member);

        Book book = new Book();
        book.setName("jpa");
        book.setStock(20);
        book.setPrice(20000);
        em.persist(book);

        int count = 2;
//        when
        Long orderId = orderService.order(member.getId(), book.getId(), count);
        Order one = orderRepository.findOne(orderId);
        one.cancel();
//        then
        assertEquals(OrderStatus.CANCEL,one.getStatus());
        assertEquals(20,book.getStock());
    }

}