package Project.OCP.Controller;

import Project.OCP.Model.*;
import Project.OCP.Repo.FolderRepo;
import Project.OCP.Repo.RespoRepo;
import Project.OCP.Service.DemandService;
import Project.OCP.Service.FolderService;
import Project.OCP.Service.PersonService;
import Project.OCP.Service.ServiceOService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@Controller
public class DemandController {
    @Autowired
    PersonService personService;
    @Autowired
    ServiceOService serviceOService;
   /* @PostMapping("/Apply")
    public String Apply(@ModelAttribute Stagiair stagiair, Model model, @RequestParam("service")int idService,
                        @RequestParam("dated") Date dated,@RequestParam("dateE")Date dateE){
        personService.SaveStagiair(stagiair);
        ServiceO service=serviceOService.getService(idService);
        Demand demand=new Demand();
        demand.setStagiair(stagiair);
        demand.setServiceO(service);
        demand.setDate_d(dated);
        demand.setDate_f(dateE);

        return "Accueil";
    }*/
   private static final String uploadFolder = "/path/to/upload/folder";
   @Autowired
    DemandService demandService;
   @Autowired
    FolderService folderService;
    @Autowired
    private FolderRepo folderRepo;
    @Autowired
    private RespoRepo respoRepo;

    @PostMapping("/Apply")
    public String apply(@ModelAttribute Stagiair stagiair, Model model, @RequestParam("service") int idService,
                        @RequestParam("dated") Date dated, @RequestParam("dateE") Date dateE,
                        @RequestParam("cv") MultipartFile cvFile, @RequestParam("assurance") MultipartFile assuranceFile,
                        @RequestParam("photo") MultipartFile photoFile,
                        @RequestParam("cinFile") MultipartFile cinFile,@RequestParam("attestation") MultipartFile attestationFile,
                        HttpServletRequest request) {

        personService.SaveStagiair(stagiair);

        ServiceO service = serviceOService.getService(idService);

        Demand demand = new Demand();
        demand.setStagiair(stagiair);
        demand.setServiceO(service);
        demand.setDate_d(dated);
        demand.setDate_f(dateE);

        demandService.SaveDemand(demand);

        Folder folder = new Folder();
        folder.setDemand(demand);

        try {
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);

            // Set cv
            if (!cvFile.isEmpty()) {
                String cvFileName = cvFile.getOriginalFilename();
                String cvFilePath = Paths.get(uploadDirectory, cvFileName).toString();
                if (cvFileName == null || cvFileName.contains("..")) {
                    model.addAttribute("invalid", "Sorry! Filename contains an invalid path sequence: " + cvFileName);
                    return "Accueil";
                }
                try {
                    File dir = new File(uploadDirectory);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(cvFilePath)));
                    stream.write(cvFile.getBytes());
                    stream.close();
                    byte[] cvImageData = cvFile.getBytes();
                    folder.setCv(cvImageData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Set image
            if (!photoFile.isEmpty()) {
                String imageFileName = photoFile.getOriginalFilename();
                String imageFilePath = Paths.get(uploadDirectory, imageFileName).toString();
                if (imageFileName == null || imageFileName.contains("..")) {
                    model.addAttribute("invalid", "Sorry! Filename contains an invalid path sequence: " + imageFileName);
                    return "Accueil";
                }
                try {
                    File dir = new File(uploadDirectory);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(imageFilePath)));
                    stream.write(photoFile.getBytes());
                    stream.close();
                    byte[] imageImageData = photoFile.getBytes();
                    folder.setImage(imageImageData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Set assurance
            if (!assuranceFile.isEmpty()) {
                String assuranceFileName = assuranceFile.getOriginalFilename();
                String assuranceFilePath = Paths.get(uploadDirectory, assuranceFileName).toString();
                if (assuranceFileName == null || assuranceFileName.contains("..")) {
                    model.addAttribute("invalid", "Sorry! Filename contains an invalid path sequence: " + assuranceFileName);
                    return "Accueil";
                }
                try {
                    File dir = new File(uploadDirectory);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(assuranceFilePath)));
                    stream.write(assuranceFile.getBytes());
                    stream.close();
                    byte[] assuranceImageData = assuranceFile.getBytes();
                    folder.setA_assurance(assuranceImageData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Set attestation
            if (!attestationFile.isEmpty()) {
                String attestationFileName = attestationFile.getOriginalFilename();
                String attestationFilePath = Paths.get(uploadDirectory, attestationFileName).toString();
                if (attestationFileName == null || attestationFileName.contains("..")) {
                    model.addAttribute("invalid", "Sorry! Filename contains an invalid path sequence: " + attestationFileName);
                    return "Accueil";
                }
                try {
                    File dir = new File(uploadDirectory);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(attestationFilePath)));
                    stream.write(attestationFile.getBytes());
                    stream.close();
                    byte[] attestationImageData = attestationFile.getBytes();
                    folder.setAttestation_s(attestationImageData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Set cin
            if (!cinFile.isEmpty()) {
                String cinFileName = cinFile.getOriginalFilename();
                String cinFilePath = Paths.get(uploadDirectory, cinFileName).toString();
                if (cinFileName == null || cinFileName.contains("..")) {
                    model.addAttribute("invalid", "Sorry! Filename contains an invalid path sequence: " + cinFileName);
                    return "Accueil";
                }
                try {
                    File dir = new File(uploadDirectory);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(cinFilePath)));
                    stream.write(cinFile.getBytes());
                    stream.close();
                    byte[] cinImageData = cinFile.getBytes();
                    folder.setCin(cinImageData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        folderService.SaveFolder(folder);
        List<ServiceO> serviceOList=serviceOService.getAllServices();
        model.addAttribute("services",serviceOList);
        return "Accueil";
    }
    @GetMapping("/ToApply")
    public String ToApplyForm(@RequestParam("serv")int id,Model model,Model model2){
        ServiceO serviceO=serviceOService.getService(id);
        model.addAttribute("service",serviceO);
        model2.addAttribute("stagiair",new Stagiair());
        return "RelatedApplyForm";
    }
   @GetMapping("/Affect")
    public String Decision(@RequestParam("decision")String decision,@RequestParam("cin")String cin,
                           @RequestParam("service")int id_service){
        Demand demand=demandService.getDemand(id_service,cin);
        demand.setStatusD(decision);
        demandService.SaveDemand(demand);
        return"redirect:/Dashboard";
    }
    @GetMapping("/ReRows")
    public String updateRefusedTrainee(@RequestParam("cin")String cin,
                                       @RequestParam("service")int id_service){
        Demand demand=demandService.getDemand(id_service,cin);
        demand.setStatusD("accepted");
        demandService.SaveDemand(demand);
        return "redirect:/ShowRefusedTrainees";
    }
}
