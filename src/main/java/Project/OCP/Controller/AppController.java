package Project.OCP.Controller;

import Project.OCP.Model.*;
import Project.OCP.Repo.AdminRepo;
import Project.OCP.Service.AdminService;
import Project.OCP.Service.DemandService;
import Project.OCP.Service.PersonService;
import Project.OCP.Service.ServiceOService;
import jakarta.servlet.http.HttpSession;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class AppController {
    @GetMapping("/OCP")
    public String ShowMain(Model model){
        List<ServiceO> serviceOList=serviceOService.getAllServices();
        model.addAttribute("services",serviceOList);
        return "Accueil";
    }
    @Autowired
    ServiceOService serviceOService;
    @GetMapping("/GoApply")
    public String GoToForm(Model model,Model model2){
        List<ServiceO> serviceOList=serviceOService.getAllServices();
        model.addAttribute("service",serviceOList);
        model2.addAttribute("stagiair",new Stagiair());
        return "ApplyForm";
    }
    @Autowired
    DemandService demandService;
    @GetMapping("/Dashboard")
    public String Dash(Model model){
        List<NewTrainee> Trainees=demandService.GetNewTrainees();
        List<StatisticModel> statistics=serviceOService.GetStatistic();
        model.addAttribute("statistics",statistics);
        model.addAttribute("Trainees",Trainees);
        return "Dashboard";
    }
    @GetMapping("/ShowDemand")
    public  String ShowDemand(@RequestParam("service")int id_service, @RequestParam("cin")String trainee, Model model){
        Trainee trainee1=demandService.getTrainee(id_service,trainee);
        model.addAttribute("trainee",trainee1);
        return "TraineeDemand";
    }
    @GetMapping("/traineeImage/{cin}/{serviceId}")
    @ResponseBody
    public ResponseEntity<byte[]> traineeImage(@PathVariable("cin") String cin, @PathVariable("serviceId") int serviceId) {
        Trainee trainee = demandService.getTrainee(serviceId, cin); // Replace with your trainee retrieval logic

        if (trainee != null && trainee.getImage() != null) {
            byte[] imageData = trainee.getImage();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/traineeDocument/{cin}/{serviceId}/{documentType}")
    @ResponseBody
    public ResponseEntity<Resource> traineeDocument(
            @PathVariable("cin") String cin,
            @PathVariable("serviceId") int serviceId,
            @PathVariable("documentType") String documentType) throws IOException {

        Trainee trainee = demandService.getTrainee(serviceId, cin);

        if (trainee != null) {
            byte[] documentData = null;
            String contentType = null;
            String filename = null;

            switch (documentType) {
                case "assurance":
                    documentData = trainee.getAssurance();
                    filename = "assurance.pdf";
                    break;
                case "CinDoc":
                    documentData = trainee.getCinDoc();
                    filename = "cin_doc.jpg";
                    break;
                case "attestation":
                    documentData = trainee.getAttestation();
                    filename = "attestation.doc";
                    break;
                case "cv":
                    documentData = trainee.getCv();
                    filename = "cv.doc";
                    break;
                default:
                    return ResponseEntity.notFound().build();
            }

            if (documentData != null) {
                ByteArrayResource resource = new ByteArrayResource(documentData);

                Tika tika = new Tika();
                contentType = tika.detect(resource.getInputStream());

                if (contentType == null) {
                    contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
                }

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(contentType));
                headers.setContentDispositionFormData("attachment", filename);

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            }
        }

        return ResponseEntity.notFound().build();
    }

@GetMapping("/ShowServices")
    public  String ShowServices(Model model){
    List<ServiceO> serviceOList=serviceOService.getAllServices();
    model.addAttribute("services",serviceOList);
    model.addAttribute("service",new ServiceO());
        return "DashServices";
}
@GetMapping("/ShowActiveTrainees")
    public String DhowActiveTrainees(Model model){
    List<ServiceO> serviceOList=serviceOService.getAllServices();
    model.addAttribute("services",serviceOList);
        List<ActiveTrainee> list=demandService.GetActiveTrainees();
        model.addAttribute("ListActiveT",list);
        return "ActiveTrainees";
}
    @GetMapping("/GetTraineesByService")
    public String getTraineesByService(@RequestParam("id") int id, @RequestParam(value = "search", required = false) String search, Model model) {
        List<ActiveTrainee> list;

        if (search != null && !search.isEmpty()) {
            System.out.println(search);
            System.out.println("NOT NULL!!!!");
            // Perform search based on the search input value
            list = demandService.GetActiveTraineesBySearch(search);
            System.out.println("the list size is : "+ list.size());
        } else {
            // Get trainees by service without search
            list = demandService.GetActiveTraineesByService(id);
        }

        model.addAttribute("ListActiveT", list);
        return "Fragments/ActiveTraineesByService :: attendanceRows";
    }
@GetMapping("/ShowRefusedTrainees")
    public  String ShowRefusedTrains(Model model){
    List<ServiceO> serviceOList=serviceOService.getAllServices();
    model.addAttribute("services",serviceOList);
    List<ActiveTrainee> list=demandService.GetRefusedTrainees();
    model.addAttribute("ListActiveT",list);
    return "Refused";
}

    @GetMapping("/GetRefusedByService")
    public String getRefusedByService(@RequestParam("id") int id, @RequestParam(value = "search", required = false) String search, Model model) {
        List<ActiveTrainee> list;
        if (search != null && !search.isEmpty()) {
            System.out.println(search);
            System.out.println("NOT NULL!!!!");
            // Perform search based on the search input value
            list = demandService.GetRefusedTraineesBySearch(search);
            System.out.println("the list size is : "+ list.size());
        } else {
            // Get trainees by service without search
            list = demandService.getRefusedTraineesByService(id);
        }
        model.addAttribute("ListActiveT", list);
        return "Fragments/RefusedTrainees :: RefusedRows";
    }
@GetMapping("/LogInAdmin")
    public String LogIn(){
        return "Login";
}
@GetMapping("/logTest")
public String NotAllowed(@RequestParam("Test")String test,Model model){
        model.addAttribute("test",test);
        return "login";
}
@Autowired
    PersonService personService;
    @Autowired
    private AdminRepo adminRepo;

    @PostMapping("/LOGIN_A")
    public String Log_A(@RequestParam("key")int key, @RequestParam("cin")String cin, HttpSession session){
       AdminO adminO= personService.getAdmin(cin);
       if(adminO != null){
        if (adminO.getCode_admin()==key){
            session.setAttribute("cin",adminO.getCin());
            return "redirect:/Dashboard";
        }
        return "redirect:/logTest?Test="+"refused";
       }
    return "redirect:/logTest?Test="+"refused";
}

@GetMapping("/ShowProfile")
    public String test(Model model,@RequestParam("cin")String cin){
    AdminO adminO= personService.getAdmin(cin);
    model.addAttribute("admin",adminO);
    return "AdminProfile";
}
@Autowired
    AdminService adminService;
@PostMapping("/UpdateAdmin")
    public String update(@RequestParam("cin")String cin,@RequestParam("FName")String f_name,
                         @RequestParam("LName")String l_name,@RequestParam("code_admin")int code,
                         @RequestParam("tel")String tel,@RequestParam("email")String email){
    AdminO adminO= personService.getAdmin(cin);
    adminO.setCode_admin(code);
    adminO.setEmail(email);
    adminO.setFName(f_name);
    adminO.setLName(l_name);
    adminO.setTel(tel);
adminService.save_admin(adminO);
    return "redirect:/ShowProfile?cin="+cin;
}

}
