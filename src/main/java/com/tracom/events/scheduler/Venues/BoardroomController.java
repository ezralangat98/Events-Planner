package com.tracom.events.scheduler.Venues;


import com.tracom.events.scheduler.Organization.Organization;
import com.tracom.events.scheduler.Organization.OrganizationRepository;
import com.tracom.events.scheduler.Organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class BoardroomController {

    @Autowired
    private BoardroomRepository boardroomRepository;
    @Autowired
    private BoardroomService boardroomService;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/add_room")
    public String showRoomAddForm(Model model) {
        model.addAttribute("boardroom", new Boardroom());

        List<String> listTv = Arrays.asList("None", "One", "Two", "More");
        model.addAttribute("listTv", listTv);

        List<String> listWhiteboard = Arrays.asList("None", "One", "Two", "More");
        model.addAttribute("listWhiteboard", listWhiteboard);

        List<String> listConference_phone = Arrays.asList("None", "One", "Two", "More");
        model.addAttribute("listConference_phone", listConference_phone);

        List<Organization>listOrganization = organizationService.listOrganization();
        model.addAttribute("listOrganization",listOrganization);

        return "Boardroom/add_room";
    }

//    @PostMapping("/create_room")
//    public String RoomCreate(Boardroom boardroom) {
//
//        boardroomRepository.save(boardroom);
//        return "Admin/admin";
//    }
    @PostMapping("/create_room") //SAVE USER
    public String RoomCreate(Boardroom boardroom, Model model) {
        try {
            boardroomService.saveBoardroom(boardroom);
        }
        catch (IllegalStateException ex) {
            model.addAttribute("error", ex.getMessage());
            return "Boardroom/add_room";
//            return "redirect:add_room";
        }
        return "redirect:list_room";
    }
    @PostMapping("/saveRoom") //SAVE USER
    public String saveRoom(Boardroom boardroom) {
        boardroomRepository.save(boardroom);

        return "redirect:list_room";
    }



    @GetMapping("/list_room")
    public String viewOrgList(Model model) {
        List<Boardroom> listRoom = boardroomRepository.findAll();
        model.addAttribute("listRoom", listRoom);
        return "Boardroom/list_room";
    }

    @RequestMapping("/delete_room/{boardroom_id}")
    public String deleteRoom(@PathVariable(name = "boardroom_id") Integer boardroom_id) {
        boardroomRepository.deleteById(boardroom_id);

//        return "redirect:list_room";
        return "redirect:/list_room";
    }



    @RequestMapping("/edit_room/{boardroom_id}")
    public ModelAndView ShowEditRoom(@PathVariable(name = "boardroom_id") Integer boardroom_id) {

        ModelAndView umv1 = new ModelAndView("Boardroom/edit_room");
        Boardroom boardroom= boardroomRepository.findByBoardroom_id(boardroom_id);
        umv1.addObject("edit_room", boardroom);

        List<Boardroom>listBoardrooms = boardroomService.listBoardrooms();
        umv1.addObject("listBoardrooms", listBoardrooms);

        List<String> listTv = Arrays.asList("None", "One", "Two", "More");
        umv1.addObject("listTv", listTv);

        List<String> listWhiteboard = Arrays.asList("None", "One", "Two", "More");
        umv1.addObject("listWhiteboard", listWhiteboard);

        List<String> listConference_phone = Arrays.asList("None", "One", "Two", "More");
        umv1.addObject("listConference_phone", listConference_phone);

        List<Organization>listOrganization = organizationService.listOrganization();
        umv1.addObject("listOrganization", listOrganization);

        return umv1;

    }

}