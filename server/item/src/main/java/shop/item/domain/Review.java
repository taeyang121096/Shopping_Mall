package shop.item.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Review {
    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String content;

}
