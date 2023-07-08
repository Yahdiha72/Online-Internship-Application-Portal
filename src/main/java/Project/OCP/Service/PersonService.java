package Project.OCP.Service;

import Project.OCP.Model.AdminO;
import Project.OCP.Model.Stagiair;
import Project.OCP.Repo.AdminRepo;
import Project.OCP.Repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PersonRepo personRepo;
    @Autowired
    AdminRepo adminRepo;

    public void SaveStagiair(Stagiair s){
        personRepo.save(s);
    }
    public AdminO getAdmin(String cin){
        return adminRepo.getAdminByCin(cin);
    }
}
