package shop.item.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.item.domain.Order;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }


}
