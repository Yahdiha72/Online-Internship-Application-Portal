package Project.OCP.Repo;

import Project.OCP.Model.Folder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FolderRepo extends JpaRepository<Folder,Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from folder where service_id=:id",nativeQuery = true)
    void DeleteFolderByService(@Param("id")int id);
}
