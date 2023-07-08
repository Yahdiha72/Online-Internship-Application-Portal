package Project.OCP.Repo;

import Project.OCP.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagiairRepo extends JpaRepository<Person,String> {
}
