package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String name;
    private int qty;
    private Double price;
    private Double discountPrice;
    private Double totalPrice;
}
