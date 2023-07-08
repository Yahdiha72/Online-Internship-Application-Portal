package Project.OCP.Service;

import Project.OCP.Model.AdminO;
import Project.OCP.Repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepo adminRepo;
    public void save_admin(AdminO a){
        adminRepo.save(a);
    }
}
