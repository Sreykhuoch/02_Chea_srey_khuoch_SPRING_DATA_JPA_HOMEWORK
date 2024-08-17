package co.istad.spring_data_jpa_homework.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_tb")

    public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "order_date", nullable = false)
        private LocalDate orderDate;

        @Column(name = "total_amount", nullable = false)
        private Float totalAmount;

        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        private StausEnum statusEnum = StausEnum.PENDING;

        @ManyToOne
        @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;

        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
        private List<ProductOrder> productOrders;
}
