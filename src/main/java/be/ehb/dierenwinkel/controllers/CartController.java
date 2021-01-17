package be.ehb.dierenwinkel.controllers;

import be.ehb.dierenwinkel.models.Order;
import be.ehb.dierenwinkel.models.Product;
import be.ehb.dierenwinkel.models.User;
import be.ehb.dierenwinkel.repositories.OrderRepository;
import be.ehb.dierenwinkel.repositories.ProductRepository;
import be.ehb.dierenwinkel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;


    //Show orders
    @GetMapping(value = "/orders")
    public String getOrders(ModelMap map, Principal principal){
        User u = userRepository.getByEmail(principal.getName());
        map.addAttribute("orders", u.getOrders());
        return "orders";
    }

    //Show cart
    @GetMapping(value = "/cart")
    public String showCart(ModelMap map, Principal principal) {
        User user = userRepository.getByEmail(principal.getName());
        map.addAttribute("products", user.getCart());
        return "cart";
    }

    //Add a new product to cart
    @GetMapping(value = "/cart/addProduct")
    public String addProductToCart(ModelMap map, Principal principal, @RequestParam("id") int id) {
        User user = userRepository.getByEmail(principal.getName());
        Optional<Product> prod = productRepository.findById(id);

        prod.ifPresent(product -> user.getCart().add(product));
        userRepository.save(user);
        return "redirect:/";
    }

    //Remove product from cart
    @GetMapping(value = "/cart/removeProduct")
    public String removeProductFromCart(ModelMap map, Principal principal, @RequestParam("id") int id){
        User user = userRepository.getByEmail(principal.getName());
        Optional<Product> prod = productRepository.findById(id);

        prod.ifPresent(product -> user.getCart().removeIf(p -> (p.getId() == product.getId())));
        userRepository.save(user);
        return "redirect:/cart";
    }

    //Checkout and redirect to orders page
    @PostMapping(value = "/cart")
    public String checkout(ModelMap map, Principal principal) {
        User user = userRepository.getByEmail(principal.getName());
        double total = user.getCart().stream().mapToDouble(Product::getPrice).sum();
        Order order = new Order(new ArrayList<>(user.getCart()), user, total);
        orderRepository.save(order);

        user.getOrders().add(order);
        user.getCart().clear();
        userRepository.save(user);

        return "redirect:/orders";
    }
}
