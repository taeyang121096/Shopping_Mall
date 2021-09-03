package shop.item.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.item.domain.Order;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

//    회원 아이디로 주문 검색
    public List<Order> findAllByString(OrderSearch orderSearch) {
        String query = "select o from Order o join o.member m";
        boolean isFisrtConditon = true;
//        주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFisrtConditon) {
                query += " where";
                isFisrtConditon = false;
            } else {
                query += " and";
            }
            query += " o.status = :status";
        }
//      회원 아이디 검색
        if (StringUtils.hasText(orderSearch.getUserId())) {
            if (isFisrtConditon) {
                query += " where";
                isFisrtConditon = false;
            } else {
                query += " and";
            }
            query += " m.userId like :userId";
        }

        TypedQuery<Order> q = em.createQuery(query, Order.class).setMaxResults(100);
        if (orderSearch.getOrderStatus() != null) {
            q = q.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getUserId())) {
            q = q.setParameter("userId", orderSearch.getUserId());
        }
        return q.getResultList();
    }
}


