package Project.OCP.Repo;

import Project.OCP.Model.AdminO;
import Project.OCP.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Person,String> {
    @Query(value = "select * from admino where cin=:cin",nativeQuery = true)
    public AdminO getAdminByCin(@Param("cin")String cin);
}
