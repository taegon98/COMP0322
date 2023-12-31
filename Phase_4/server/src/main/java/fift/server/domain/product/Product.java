package fift.server.domain.product;

import fift.server.domain.gifticon.Gifticon;
import fift.server.domain.orderdetail.OrderDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    private Long productId;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList=new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Gifticon> gifticons = new ArrayList<>();

    private String productName;
    private String Description;
    private Double Price;
    private String Photo;


}
