package Project.OCP.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    private String cin;
    @Column
    private String FName;
    @Column
     private    String    LName;
    @Column
    private String tel;
    @Column
      private    String   gender;
    @Column
    private Date birth_date;
    @Column
    private String email;


}
