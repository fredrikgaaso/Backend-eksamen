package PGR209.Eksam.Model;

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
public class Subassembly {
    @Id
    @GeneratedValue(generator = "subassembly_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "subassembly_seq_gen", sequenceName = "subassembly_seq", allocationSize = 1)
    @Column(name = "subassembly_id")
    private Long subassemblyId;
    @Column(name = "subassembly_name")
    private String subassemblyName;

    @OneToMany
    @JoinTable(name = "Subassembly_Parts",
            joinColumns = {@JoinColumn(name = "subassembly_id")},
            inverseJoinColumns = {@JoinColumn ( name = "parts_id")})
    private List<Parts> parts = new ArrayList<>();



    public Subassembly(String subassemblyName) {
        this.subassemblyName = subassemblyName;
    }
}
