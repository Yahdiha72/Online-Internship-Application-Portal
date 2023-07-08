package Project.OCP.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_file;
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    @Lob
    private byte[] image;
    @Column(name = "cv", length = Integer.MAX_VALUE, nullable = true)
    @Lob
    private byte[] cv;
    @Column(name = "cin", length = Integer.MAX_VALUE, nullable = true)
    @Lob
    private byte[] cin;
    @Column(name = "a_assurance", length = Integer.MAX_VALUE, nullable = true)
    @Lob
    private byte[] a_assurance;
    @Column(name = "attestation_s", length = Integer.MAX_VALUE, nullable = true)
    @Lob
    private byte[] attestation_s;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="Service_Id"),
            @JoinColumn(name="Stage_id")
    })
    private Demand demand;

}
