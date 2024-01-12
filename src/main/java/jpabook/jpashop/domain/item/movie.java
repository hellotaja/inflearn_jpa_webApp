package jpabook.jpashop.domain.item;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class movie extends Item {

    private String director;
    private String actor;
}
