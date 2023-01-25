package ba.sum.fsre.blagajna.controller;

import ba.sum.fsre.blagajna.model.Konobar;
import ba.sum.fsre.blagajna.model.KonobarDetails;
import ba.sum.fsre.blagajna.repositories.KonobarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class KonobarController {
    @Autowired
    private KonobarRepository konobarRepo;



    @GetMapping("/users")
    public String listUsers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KonobarDetails userDetails = (KonobarDetails) authentication.getPrincipal();
        List<Konobar> listUsers = konobarRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("activeLink", "Korisnici");
        return "users";
    }
}