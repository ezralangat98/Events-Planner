package com.tracom.events.scheduler.Organization;

import com.tracom.events.scheduler.Venues.Boardroom;
import com.tracom.events.scheduler.Venues.BoardroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private BoardroomService boardroomService;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/add_org")
    public String showOrganizationAddForm(Model model) {
        model.addAttribute("organization", new Organization());

        List<Boardroom>listBoardrooms=boardroomService.listBoardrooms();
        model.addAttribute("listBoardrooms", listBoardrooms);

        return "Organization/add_org";
    }

    @PostMapping("/create_organization")
    public String OrgCreate(Organization organization, Model model) {
        try {
            organizationService.saveOrganization(organization);
        }
        catch (IllegalStateException ex) {
            model.addAttribute("error", ex.getMessage());
            return "Organization/add_org";
        }
        return "Admin/admin";
    }
    @PostMapping( "/saveOrganization")
    public  String saveOrg(Organization organization){
        organizationRepository.save(organization);

        return "Admin/admin";
    }

    @GetMapping("/list_organization")
    public String viewOrgList(Model model) {
        List<Organization> listOrg = organizationRepository.findAll();
        model.addAttribute("listOrg", listOrg);
        return "Organization/list_organization";
    }

    @RequestMapping("/delete_org/{organization_id}")
    public String deleteOrg(@PathVariable(name = "organization_id") Integer organization_id) {
        organizationRepository.deleteById(organization_id);
        return "Organization/list_organization";
    }

    @RequestMapping("/edit_org/{organization_id}")
    public ModelAndView ShowEditOrg(@PathVariable(name = "organization_id") Integer organization_id) {
        ModelAndView umv = new ModelAndView("Organization/edit_org");
        Organization organization = organizationRepository.findByOrganization_id(organization_id);
        umv.addObject("edit_org", organization);

        List<Boardroom> listBoardrooms=boardroomService.listBoardrooms();
        umv.addObject("listBoardrooms", listBoardrooms);

        List<Organization>listOrganization = organizationService.listOrganization();
        umv.addObject("listBoardrooms", listBoardrooms);


        return umv;

    }
}