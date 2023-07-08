package Project.OCP.Repo;

import Project.OCP.Model.Demand;
import Project.OCP.Model.NewTrainee;
import Project.OCP.Model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DemandRepo extends JpaRepository<Demand,String> {
    @Query(value = " select T.cin, concat(T.fname,' ',T.lname) as fullname,S.id, \n" +
            "            S.service_name,T.email, D.date_d,D.date_f from stagiair T,serviceO S,Demand D\n" +
            "            where S.id=D.service_id and T.cin=D.stage_id and D.statusd is null",nativeQuery = true)
     List<Object[]> NewTrainees();
    @Query(value = "select t.cin, concat(t.fname,' ',t.lname), t.email, t.tel, f.id_file, d.service_id, f.a_assurance, f.attestation_s, f.cin, f.cv, f.image " +
            "from stagiair t, folder f, demand d " +
            "where t.cin = d.stage_id and d.stage_id = f.stage_id and d.service_id = :id_serv and d.stage_id = :id_tr",
            nativeQuery = true)
    List<Object[]> GetTraineeDemand(@Param("id_serv") int id_serv, @Param("id_tr") String id_tr);


    @Query(value = "select * from demand where stage_id=:cin and service_id=:id",nativeQuery = true)
    Demand GetDemand(@Param("id")int id,@Param("cin")String cin);
    @Modifying
    @Transactional
    @Query(value = "delete from demand where service_id=:id",nativeQuery = true)
    void DeleteDemandByService(@Param("id")int id);


    @Query(value = "select S.cin,concat(S.fname,' ',S.lname) as nom,S.email,SS.service_name,SS.id,d.date_d,date_f\n" +
            " from stagiair S,demand d,serviceo SS where S.cin=d.stage_id\n" +
            " and d.service_id=SS.id and d.statusd='accepted'",nativeQuery = true)
    List<Object[]> GetActiveTrainees();

    @Query(value = "select S.cin,concat(S.fname,' ',S.lname) as nom,S.email,SS.service_name,SS.id,d.date_d,date_f\n" +
            " from stagiair S,demand d,serviceo SS where S.cin=d.stage_id\n" +
            " and d.service_id=SS.id and d.statusd='accepted'  and SS.id=:id",nativeQuery = true)
    List<Object[]> GetTraineesByService(@Param("id")int id);
    @Query(value = "SELECT S.cin, CONCAT(S.fname,' ',S.lname) AS nom, S.email, SS.service_name, SS.id, d.date_d, date_f " +
            "FROM stagiair S, demand d, serviceo SS " +
            "WHERE S.cin = d.stage_id AND d.service_id = SS.id AND d.statusd = 'accepted' AND " +
            "(S.email LIKE %:key% OR SS.service_name LIKE %:key% OR S.cin LIKE %:key%)",
            nativeQuery = true)
    List<Object[]> getTraineesBySearch(@Param("key") String key);
    @Query(value = "select S.cin,concat(S.fname,' ',S.lname) as nom,S.email,SS.service_name,SS.id,d.date_d,date_f\n" +
            " from stagiair S,demand d,serviceo SS where S.cin=d.stage_id\n" +
            " and d.service_id=SS.id and d.statusd='refused'",nativeQuery = true)
    List<Object[]> getRefusedTrainees();
    @Query(value = "select S.cin,concat(S.fname,' ',S.lname) as nom,S.email,SS.service_name,SS.id,d.date_d,date_f\n" +
            " from stagiair S,demand d,serviceo SS where S.cin=d.stage_id\n" +
            " and d.service_id=SS.id and d.statusd='refused'  and SS.id=:id",nativeQuery = true)
    List<Object[]> getRefusedByService(@Param("id")int id);
    @Query(value = "SELECT S.cin, CONCAT(S.fname,' ',S.lname) AS nom, S.email, SS.service_name, SS.id, d.date_d, date_f " +
            "FROM stagiair S, demand d, serviceo SS " +
            "WHERE S.cin = d.stage_id AND d.service_id = SS.id AND d.statusd = 'refused' AND " +
            "(S.email LIKE %:key% OR SS.service_name LIKE %:key% OR S.cin LIKE %:key%)",
            nativeQuery = true)
    List<Object[]> getRefusedBySearch(@Param("key") String key);

}
