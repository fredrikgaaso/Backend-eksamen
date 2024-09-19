package PGR209.Eksam.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Address {
    @Id
    @GeneratedValue(generator = "address_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "address_seq_gen", sequenceName = "address_seq", allocationSize = 1)
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "address_name")
    private String addressName;

    @ManyToMany(mappedBy = "addresses", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Customer> customers = new ArrayList<>();

    public Address(String addressName) {
        this.addressName = addressName;
    }
}
