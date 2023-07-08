package Project.OCP.Repo;

import Project.OCP.Model.ServiceO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepo extends JpaRepository<ServiceO,Integer> {
    @Query(value = " SELECT\n" +
            "  S.id,\n" +
            "  S.service_name,\n" +
            "  ROUND((COALESCE(demand_count, 0) / total_demand) * 100, 0) AS demand_percentage\n" +
            "FROM\n" +
            "  serviceo S\n" +
            "LEFT JOIN (\n" +
            "  SELECT\n" +
            "    service_id,\n" +
            "    COUNT(*) AS demand_count\n" +
            "  FROM\n" +
            "    demand\n" +
            "  GROUP BY\n" +
            "    service_id\n" +
            ") D ON S.id = D.service_id\n" +
            "CROSS JOIN (\n" +
            "  SELECT\n" +
            "    COUNT(*) AS total_demand\n" +
            "  FROM\n" +
            "    demand\n" +
            ") T;\n",nativeQuery = true)
    List<Object[]> StatisticServices();
}
