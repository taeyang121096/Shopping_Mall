package shop.item.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.item.Book;
import shop.item.domain.item.Item;
import shop.item.exception.NotEnoughStockException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired ItemRepository itemRepository;
    @Autowired
    EntityManager em;

    @Test
    void 상품등록_검색() throws Exception {
//      given
        Book book = setItem("책1",20000,200,"태양","asas123");
//        when
        itemRepository.save(book);

        em.flush();
        em.clear();

        Item find = itemRepository.findOne(book.getId());
        find.setStock(30);
        itemRepository.save(find);
//        then
        assertThat(find.getId()).isEqualTo(book.getId());
        assertThat(book.getStock()).isNotEqualTo(find.getStock());
        assertThat(itemRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    void 상품_검색() throws Exception {
//        given
        Book book1 = setItem("책1",20000,200,"태양","asas123");
        Book book2 = setItem("책2",20000,300,"태양","as123");
//        when
        itemRepository.save(book1);
        itemRepository.save(book2);
        Item one = itemRepository.findOne(book1.getId());
//        then
        assertThat(one.getId()).isNotEqualTo(book2.getId());
    }

    @Test
    void 모든_상품_검색() throws Exception {
        //        given
        Book book1 = setItem("책1",20000,200,"태양","asas123");
        Book book2 = setItem("책2",20000,300,"태양","as123");
//        when
        itemRepository.save(book1);
        itemRepository.save(book2);
        List<Item> all = itemRepository.findAll();
//        then
        assertThat(all.size()).isEqualTo(2);
    }


    public Book setItem(String name, int price, int stock, String author, String isbn){
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStock(stock);
        book.setAuthor(author);
        book.setIsbn(isbn);
        return book;
    }
}