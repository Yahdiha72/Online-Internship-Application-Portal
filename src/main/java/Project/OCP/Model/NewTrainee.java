package Project.OCP.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class NewTrainee {
    private String cin;
    private String FullName;
    private int service_id;
    private String service_name;
    private String email;
    private Date date_d,date_f;

}
