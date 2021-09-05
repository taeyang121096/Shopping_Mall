package shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.Address;
import shop.item.domain.Member;
import shop.item.domain.OrderItem;
import shop.item.domain.item.Book;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
        public void dbINit1(){
            Address address = new Address("seoul","1673-32","123213");
            Member member = Member.createMember("lso","lsolyh","tae","taeyang","010101",address);
            em.persist(member);

            Book book1 = createBook("jpa1", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("jpa2", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem.createOrderItem(book2, 20000, 2);


        }



        private Book createBook(String name, int price, int stock) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStock(stock);
            return book;
        }
    }
}
