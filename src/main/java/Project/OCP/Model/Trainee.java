package Project.OCP.Model;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trainee {
    private String cin;
    private String full_name;
    private String email;
    private String tel;
    private int id_file,service_id;
    @Lob
    private byte[] cv;
    @Lob
    private byte[] attestation;
    @Lob
    private byte[] assurance;
    @Lob
    private byte[] CinDoc;
    @Lob
    private byte[] image;
}

