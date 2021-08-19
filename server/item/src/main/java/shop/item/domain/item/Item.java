package shop.item.domain.item;

import lombok.Getter;
import lombok.Setter;
import shop.item.domain.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stock;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
