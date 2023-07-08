package Project.OCP.Service;

import Project.OCP.Model.ServiceO;
import Project.OCP.Model.StatisticModel;
import Project.OCP.Repo.ServicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceOService {
@Autowired
    ServicesRepo servicesRepo;
public List<ServiceO> getAllServices(){
    return  servicesRepo.findAll();
}
public ServiceO getService(int id){
    return servicesRepo.getReferenceById(id);
}


public List<StatisticModel> GetStatistic(){
    List<Object[]> Statistics= servicesRepo.StatisticServices();
    List<StatisticModel> statisticModels=new ArrayList<>();
    for(Object[] row : Statistics){
        StatisticModel stm=new StatisticModel();
        stm.setId((int) row[0]);
        stm.setService((String) row[1]);
        stm.setAvg((BigDecimal) row[2]);
        statisticModels.add(stm);
    }
    return statisticModels;
}

public void deleteService(int id){
    servicesRepo.deleteById(id);
    System.out.println("deleted !!!!!!!");
}

public void Save(ServiceO s){
    servicesRepo.save(s);
}
}
