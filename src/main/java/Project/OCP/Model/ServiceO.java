package Project.OCP.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ServiceO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String service_name;
    @Column
    private String service_description;
    @OneToMany(mappedBy = "serviceO")
    private Set<Demand> demands = new HashSet<>();

}
