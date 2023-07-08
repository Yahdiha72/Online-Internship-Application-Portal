package Project.OCP.Service;

import Project.OCP.Model.*;
import Project.OCP.Repo.DemandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DemandService {
    @Autowired
    DemandRepo demandRepo;

    public void SaveDemand(Demand demand) {
        demandRepo.save(demand);
    }

    public List<NewTrainee> GetNewTrainees() {
        List<Object[]> rows = demandRepo.NewTrainees();
        List<NewTrainee> trainees = new ArrayList<>();
        for (Object[] row : rows) {
            NewTrainee trainee = new NewTrainee();
            trainee.setCin((String) row[0]);
            trainee.setFullName((String) row[1]);
            trainee.setService_id((int) row[2]);
            trainee.setService_name((String) row[3]);
            trainee.setEmail((String) row[4]);
            trainee.setDate_d((Date) row[5]);
            trainee.setDate_f((Date) row[6]);
            trainees.add(trainee);
        }
        return trainees;
    }
    public Trainee getTrainee(int service, String trainee) {
        List<Object[]> queryResult = demandRepo.GetTraineeDemand(service, trainee);

        if (!queryResult.isEmpty()) {
            Object[] result = queryResult.get(0); // Assuming only one result is expected
            Trainee trainee1 = new Trainee();
            trainee1.setCin((String) result[0]);
            trainee1.setFull_name((String) result[1]);
            trainee1.setEmail((String) result[2]);
            trainee1.setTel((String) result[3]);
            trainee1.setId_file((int) result[4]);
            trainee1.setService_id((int) result[5]);
            trainee1.setAssurance((byte[]) result[6]);
            trainee1.setAttestation((byte[]) result[7]);
            trainee1.setCinDoc((byte[]) result[8]);
           trainee1.setCv((byte[]) result[9]);
            trainee1.setImage((byte[]) result[10]);
            return trainee1;
        }

        return null; // Return null if no result is found
    }

    public Demand getDemand(int id,String cin){
        return demandRepo.GetDemand(id,cin);
    }

    public void DeleteDemandByServiceID(int id){
        demandRepo.DeleteDemandByService(id);
    }

public List<ActiveTrainee> GetActiveTrainees(){
        List<ActiveTrainee> list=new ArrayList<>();
    List<Object[]> ls=demandRepo.GetActiveTrainees();
    for(Object[] obj : ls){
        ActiveTrainee at=new ActiveTrainee();
        at.setCin((String) obj[0]);
        at.setName((String) obj[1]);
        at.setEmail((String) obj[2]);
        at.setService((String) obj[3]);
        at.setId_service((int) obj[4]);
        at.setDate_d((Date) obj[5]);
        at.setDate_f((Date) obj[6]);
        list.add(at);
    }
return list;
}
    public List<ActiveTrainee> GetActiveTraineesByService(int id){
        List<ActiveTrainee> list=new ArrayList<>();
        List<Object[]> ls=demandRepo.GetTraineesByService(id);
        for(Object[] obj : ls){
            ActiveTrainee at=new ActiveTrainee();
            at.setCin((String) obj[0]);
            at.setName((String) obj[1]);
            at.setEmail((String) obj[2]);
            at.setService((String) obj[3]);
            at.setId_service((int) obj[4]);
            at.setDate_d((Date) obj[5]);
            at.setDate_f((Date) obj[6]);
            list.add(at);
            System.out.println("THE ID:"+ id);
            System.out.println("THE TRAINEE :"+at.toString());
        }
        return list;
    }

    public List<ActiveTrainee> GetActiveTraineesBySearch(String key){
        List<ActiveTrainee> list=new ArrayList<>();
        List<Object[]> ls=demandRepo.getTraineesBySearch(key);
        for(Object[] obj : ls){
            ActiveTrainee at=new ActiveTrainee();
            at.setCin((String) obj[0]);
            at.setName((String) obj[1]);
            at.setEmail((String) obj[2]);
            at.setService((String) obj[3]);
            at.setId_service((int) obj[4]);
            at.setDate_d((Date) obj[5]);
            at.setDate_f((Date) obj[6]);
            list.add(at);
            System.out.println("THE key:"+ key);
            System.out.println("THE TRAINEE :"+at.toString());
        }
        return list;
    }


    public List<ActiveTrainee> GetRefusedTrainees(){
        List<ActiveTrainee> list=new ArrayList<>();
        List<Object[]> ls=demandRepo.getRefusedTrainees();
        for(Object[] obj : ls){
            ActiveTrainee at=new ActiveTrainee();
            at.setCin((String) obj[0]);
            at.setName((String) obj[1]);
            at.setEmail((String) obj[2]);
            at.setService((String) obj[3]);
            at.setId_service((int) obj[4]);
            at.setDate_d((Date) obj[5]);
            at.setDate_f((Date) obj[6]);
            list.add(at);
        }
        return list;
    }
    public List<ActiveTrainee> getRefusedTraineesByService(int id){
        List<ActiveTrainee> list=new ArrayList<>();
        List<Object[]> ls=demandRepo.getRefusedByService(id);
        for(Object[] obj : ls){
            ActiveTrainee at=new ActiveTrainee();
            at.setCin((String) obj[0]);
            at.setName((String) obj[1]);
            at.setEmail((String) obj[2]);
            at.setService((String) obj[3]);
            at.setId_service((int) obj[4]);
            at.setDate_d((Date) obj[5]);
            at.setDate_f((Date) obj[6]);
            list.add(at);
            System.out.println("THE ID:"+ id);
            System.out.println("THE REFUSED TRAINEE :"+at.toString());
        }
        return list;
    }

    public List<ActiveTrainee> GetRefusedTraineesBySearch(String key){
        List<ActiveTrainee> list=new ArrayList<>();
        List<Object[]> ls=demandRepo.getRefusedBySearch(key);
        for(Object[] obj : ls){
            ActiveTrainee at=new ActiveTrainee();
            at.setCin((String) obj[0]);
            at.setName((String) obj[1]);
            at.setEmail((String) obj[2]);
            at.setService((String) obj[3]);
            at.setId_service((int) obj[4]);
            at.setDate_d((Date) obj[5]);
            at.setDate_f((Date) obj[6]);
            list.add(at);
            System.out.println("THE key:"+ key);
            System.out.println("THE REFUSED TRAINEE :"+at.toString());
        }
        return list;
    }
}