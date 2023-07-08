package Project.OCP.Model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActiveTrainee {
    private String cin,name,email,service;
    private int id_service;
    private Date date_d,date_f;

}
