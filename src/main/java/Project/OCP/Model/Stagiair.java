package Project.OCP.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stagiair extends Person{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Num;
    @ManyToOne
    @JoinColumn(name = "R_cin")
    private Responsable responsable;
    @OneToMany(mappedBy = "stagiair")
    private Set<Demand> demands = new HashSet<>();


}
