package be.ehb.dierenwinkel.controllers;

import be.ehb.dierenwinkel.models.Product;
import be.ehb.dierenwinkel.models.User;
import be.ehb.dierenwinkel.repositories.OrderRepository;
import be.ehb.dierenwinkel.repositories.ProductRepository;
import be.ehb.dierenwinkel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/")
    public String showAllProducts(ModelMap map) {
        Iterable<Product> products = productRepository.findAll();
        map.addAttribute("productList", products);
        return "index";
    }
    @GetMapping(value = {"/{category}"})
    public String showByCategory(ModelMap map, @PathVariable(name = "category") String category) {
        map.addAttribute("productList", productRepository.getAllByCategory(category));
        return "index";
    }

}
