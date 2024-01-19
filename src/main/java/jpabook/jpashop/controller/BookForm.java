package jpabook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id;

    @NotEmpty(message = "상품 이름은 필수 입니다")
    private String name;

    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

}
