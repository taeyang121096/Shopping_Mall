package shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.*;
import shop.item.domain.item.Book;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit1(){
            Address address = new Address("seoul","1673-32","123213");
            Member member = Member.createMember("lso","lsolyh","tae","taeyang","010101",address);
            em.persist(member);

            Book book1 = createBook("jpa1", 10000, 100,"taeyang","123-123");
            em.persist(book1);

            Book book2 = createBook("jpa2", 20000, 100,"hyoun","321-123");
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
            Order order = Order.createOrder(member, createDelivery(member), createReview("작성해주세요"), orderItem1, orderItem2);
            em.persist(order);
        }
        public void dbInit2(){
            Address address = new Address("seoul","1673-31","122213");
            Member member = Member.createMember("www","lsowyh","sunny","tae","010101123",address);
            em.persist(member);

            Book book1 = createBook("spring", 10000, 100,"taeyang","123-124");
            em.persist(book1);

            Book book2 = createBook("spring", 20000, 200,"hyoun","321-126");
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 4);
            Order order = Order.createOrder(member, createDelivery(member), createReview("작성해주세요"), orderItem1, orderItem2);
            em.persist(order);
        }



        private Book createBook(String name, int price, int stock,String author,String isbn) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStock(stock);
            book.setAuthor(author);
            book.setIsbn(isbn);
            return book;
        }
        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            delivery.setStatus(DeliveryStatus.READY);
            return delivery;
        }
        private Review createReview(String def){
            Review review = new Review();
            review.setContent(def);
            return review;
        }
    }
}
