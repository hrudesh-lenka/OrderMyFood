package com.ordermanagementservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String restaurantName;

    private String paymentId;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name ="order_id", referencedColumnName = "id")
    private List<OrderItems> orderItems;

    private BigDecimal totalPrice;

    private String status;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", restaurantName='" + restaurantName + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", user=" + user +
                ", orderItems=" + orderItems +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
