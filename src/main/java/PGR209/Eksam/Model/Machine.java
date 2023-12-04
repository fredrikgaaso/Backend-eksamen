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
public class Machine {
    @Id
    @GeneratedValue(generator = "machine_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "machine_seq_gen", sequenceName = "machine_seq", allocationSize = 1)
    @Column(name = "machine_id")
    private Long machineId;
    @Column(name = "machine_name")
    private String machineName;

    @ManyToOne
    @JoinColumn(name = "subassembly_id")
    private Subassembly subassembly;


    public Machine(String machineName) {
        this.machineName = machineName;
    }
}
