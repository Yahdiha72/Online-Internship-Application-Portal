package Project.OCP.Controller;

import Project.OCP.Model.ServiceO;
import Project.OCP.Repo.ServicesRepo;
import Project.OCP.Service.DemandService;
import Project.OCP.Service.FolderService;
import Project.OCP.Service.ServiceOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServicesController {
    @Autowired
    ServiceOService serviceOService;
    @Autowired
    FolderService folderService;
    @Autowired
    DemandService demandService;
    @Autowired
    private ServicesRepo servicesRepo;

    @GetMapping("/DeleteService")
    public String deleteS(@RequestParam("id")int id){
        folderService.DeleteFolderByServiceID(id);
        demandService.DeleteDemandByServiceID(id);
        serviceOService.deleteService(id);
        System.out.println("deleted successfully");
        return "redirect:/ShowServices";
    }
    @GetMapping("UpdateS")
    public String go_update(Model model,@RequestParam("id")int id){
        ServiceO serviceO= serviceOService.getService(id);
        model.addAttribute("service",serviceO);
        return "DashService";
    }
    @PostMapping("/UpdateService")
    public String updateS(@RequestParam("id")int id,@RequestParam("service_name")String name,
                          @RequestParam("service_des")String des){
        ServiceO serviceO= serviceOService.getService(id);
        serviceO.setService_name(name);
        serviceO.setService_description(des);
        serviceOService.Save(serviceO);
        System.out.println("Updated!!!");
        return "redirect:/ShowServices";
    }

    @PostMapping("/SaveService")
    public String save(@ModelAttribute("service")ServiceO serviceO){
        serviceOService.Save(serviceO);
        return  "redirect:/ShowServices";
    }
}
