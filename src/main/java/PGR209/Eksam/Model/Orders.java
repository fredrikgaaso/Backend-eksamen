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

   @OneToMany
   @JoinTable(name = "Orders_Machine",
           joinColumns = {@JoinColumn(name = "order_id")},
           inverseJoinColumns = {@JoinColumn ( name = "machine_id")})
   private List<Machine> machine = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("Orders")
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
