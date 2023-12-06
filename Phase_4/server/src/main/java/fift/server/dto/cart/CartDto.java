package fift.server.dto.cart;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CartDto {

    private Long id;
    private int count;
}
