package PGR209.Eksam.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(generator = "order_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order_seq_gen", sequenceName = "order_seq", allocationSize = 1)
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "order_name")
    private String orderName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    @JoinColumn(name = "order_id")
    private List<Machine> machines = new ArrayList<>();


    public Orders(String orderName) {
        this.orderName = orderName;
    }
}
