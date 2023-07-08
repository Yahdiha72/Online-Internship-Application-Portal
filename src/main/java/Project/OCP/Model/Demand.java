package Project.OCP.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DemandID.class)
@Entity
public class Demand {
    @Id
    @ManyToOne
    @JoinColumn(name="Service_Id")
    private ServiceO serviceO;
    @Id
    @ManyToOne
    @JoinColumn(name="Stage_id")
    private Stagiair stagiair;
    @Column
    private Date  date_d;
    @Column
    private Date date_f;
    @Column(name="StatusD", columnDefinition="varchar(30) default 'pending'")
    private  String StatusD;
    @OneToMany(mappedBy = "demand")
    private Set<Folder> folders = new HashSet<>();
}
