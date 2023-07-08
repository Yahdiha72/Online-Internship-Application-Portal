package Project.OCP.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Responsable extends Person{
    private int accsess_number;
    @OneToMany(mappedBy = "responsable")
    private Set<Stagiair> stagiairs = new HashSet<>();
}
