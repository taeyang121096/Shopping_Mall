package shop.item.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.item.domain.item.Book;
import shop.item.domain.item.Item;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    ItemService itemService;

    @Test
    void 아이템_저장() throws Exception {
//        given
        Book book = setItem("책1",20000,200,"태양","asas123");
        itemService.saveItem(book);
//        when
        Item findItem = itemService.findOne(book.getId());
//        then
        assertThat(book.getId()).isEqualTo(findItem.getId());
    }

    @Test
    void 아이템_검색() throws Exception {
//        given
        Book book1 = setItem("책1",20000,200,"태양","asas123");
        Book book2 = setItem("책2",30000,200,"태양","asas1234");
        itemService.saveItem(book1);
        itemService.saveItem(book2);
//        when
        Item findOne = itemService.findOne(book1.getId());
        List<Item> findAll = itemService.findItems();
//        then
        assertThat(findOne.getId()).isEqualTo(book1.getId());
        assertThat(findAll.size()).isEqualTo(2);
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