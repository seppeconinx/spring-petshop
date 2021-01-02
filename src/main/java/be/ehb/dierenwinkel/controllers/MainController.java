package be.ehb.dierenwinkel.controllers;

import be.ehb.dierenwinkel.models.Item;
import be.ehb.dierenwinkel.models.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private final ItemDAO itemDAO;

    @Autowired
    public MainController(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String toonAlles(ModelMap map){
        map.addAttribute("all", itemDAO.findAll());
        return "home";
    }

    @RequestMapping(value = {"/{categorie}", "/home/{categorie}"}, method = RequestMethod.GET)
    public String toonPerCategorie(ModelMap map, @PathVariable(name = "categorie") String categorie){
        map.addAttribute("all", itemDAO.findAllByCategory(categorie));
        return "home";
    }

    @RequestMapping(value = {"/add/{id}", "/index/add/{id}"}, method = RequestMethod.GET)
    public String toevoegenAanMand(ModelMap map, @PathVariable(name ="id") int id){
        Item item = itemDAO.findById(id).get();

        return "redirect:/home";
    }
}
