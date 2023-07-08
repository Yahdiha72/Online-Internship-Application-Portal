package Project.OCP.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemandID implements Serializable {
private ServiceO serviceO;
private Stagiair stagiair;


}
