package shop.item.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.item.domain.item.Item;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        }else{
            Item findItem = em.find(Item.class, item.getId());
            findItem.setCategories(item.getCategories());
            findItem.setName(item.getName());
            findItem.setPrice(item.getPrice());
            findItem.setStock(item.getStock());
        }

    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}