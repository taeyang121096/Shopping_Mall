package shop.item.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.*;
import shop.item.domain.item.Book;
import shop.item.exception.NotEnoughStockException;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    EntityManager em;
    @Test
    void 주문등록() throws Exception{
//        given
        Address address = new Address("seoul","1596-3","123123");
        Member member = Member.createMember("sun", "lso", "juju", "juyoung", "010-010010", address);
        em.persist(member);
        Book book = getBook(20, 10000, "jpa");
        em.persist(book);
        int count =2;
        OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), count);
        Review review = new Review();

        Delivery delivery = new Delivery();
        delivery.setAddress(address);

        Order order = Order.createOrder(member, delivery, review, orderItem);
//        when

        orderRepository.save(order);
        Order one = orderRepository.findOne(order.getId());
//        then

        assertEquals(order.getId(),one.getId());
        assertEquals(18,book.getStock());
        assertEquals(10000*count,order.getTotalPrice());
        assertEquals(OrderStatus.ORDER,order.getStatus());
        assertEquals(1,order.getOrderItems().size());


    }

    @Test
    void 재고초과() throws Exception{
//        given
        assertThrows(NotEnoughStockException.class,()->{
            Address address = new Address("seoul","1596-3","123123");
            Member member = Member.createMember("sun", "lso", "juju", "juyoung", "010-010010", address);
            em.persist(member);
            Book book = getBook(20, 10000, "jpa");
            em.persist(book);
            int count = 21;
            OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), count);

            Review review = new Review();

            Delivery delivery = new Delivery();
            delivery.setAddress(address);

            Order order = Order.createOrder(member, delivery, review, orderItem);
//        when
            orderRepository.save(order);
        });

    }

    @Test
    void 주문취소() throws Exception{
//        given

        Address address = new Address("seoul","1596-3","123123");
        Member member = Member.createMember("sun", "lso", "juju", "juyoung", "010-010010", address);
        em.persist(member);
        Book book = getBook(20, 10000, "jpa");
        em.persist(book);
        int count = 18;
        OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), count);

        Review review = new Review();

        Delivery delivery = new Delivery();
        delivery.setAddress(address);

        Order order = Order.createOrder(member, delivery, review, orderItem);
//        when

        orderRepository.save(order);
        Order one = orderRepository.findOne(order.getId());
        one.cancel();

//        then
        assertEquals(OrderStatus.CANCEL,one.getStatus());
        assertEquals(20,book.getStock());


    }

    private Book getBook(int stock, int price, String jpa) {
        Book book = new Book();
        book.setStock(stock);
        book.setPrice(price);
        book.setName(jpa);
        return book;
    }


}