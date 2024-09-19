package PGR209.Eksam.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Parts {
    @Id
    @GeneratedValue(generator = "parts_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "parts_seq_gen", sequenceName = "parts_seq", allocationSize = 1)
    @Column(name = "parts_id")
    private Long partsId;
    @Column(name = "parts_name")
    private String partsName;


    public Parts(String partsName) {
        this.partsName = partsName;
    }
}
