package ba.sum.fsre.blagajna.controller;

import ba.sum.fsre.blagajna.model.Konobar;
import ba.sum.fsre.blagajna.model.KonobarDetails;
import ba.sum.fsre.blagajna.model.Proizvodi;
import ba.sum.fsre.blagajna.repositories.KonobarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("activeLink", "Konobar");
        return "users";
    }

    @GetMapping("users/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
       Konobar konobar = konobarRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid konobar Id:" + id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
        model.addAttribute("activeLink", "Konobar");
        model.addAttribute("konobarDetails", konobarDetails);
        model.addAttribute("konobar", konobar);
        return "edit_users";
    }

    @PostMapping("users/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Konobar konobar, BindingResult result, Model model) {
        if (result.hasErrors()) {konobar.setId(id);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
            model.addAttribute("activeLink", "Konobar");
            model.addAttribute("konobarDetails", konobarDetails);
            model.addAttribute("konobar", konobar);
            return "edit_users";
        }

        konobarRepo.save(konobar);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Konobar konobar = konobarRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid konobar Id:" + id));
        konobarRepo.delete(konobar);
        return "redirect:/users";
    }
}