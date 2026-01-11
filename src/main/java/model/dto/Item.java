package model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    private String id;
    private String name;
    private String category;
    private String size;
    private double price;
    private int qty;
    private boolean available;
    private double total;

    public boolean getAvailable() {
        return this.available;
    }

}
