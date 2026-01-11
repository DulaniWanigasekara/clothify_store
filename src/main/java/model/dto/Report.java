package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Report {
    public String orderId;
    public String customerId;
    public String name;
}
