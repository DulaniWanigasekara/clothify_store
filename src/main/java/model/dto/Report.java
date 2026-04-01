package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Report {
    private String orderId;
    private String customerId;
    private String name;
}
