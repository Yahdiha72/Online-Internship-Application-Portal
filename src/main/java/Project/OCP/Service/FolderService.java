package Project.OCP.Service;

import Project.OCP.Model.Folder;
import Project.OCP.Repo.FolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderService {
    @Autowired
    FolderRepo folderRepo;
    public void SaveFolder(Folder f){
        folderRepo.save(f);
    }
    public void DeleteFolderByServiceID(int id){
        folderRepo.DeleteFolderByService(id);
    }
}
