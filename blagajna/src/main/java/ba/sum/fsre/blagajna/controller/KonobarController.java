package ba.sum.fsre.blagajna.controller;

import ba.sum.fsre.blagajna.model.Konobar;
import ba.sum.fsre.blagajna.model.KonobarDetails;
import ba.sum.fsre.blagajna.repositories.KonobarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class KonobarController {
    @Autowired
    private KonobarRepository konobarRepo;

    @GetMapping("/users")
    public String listUsers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KonobarDetails userDetails = (KonobarDetails) authentication.getPrincipal();
        List<Konobar> listUsers = konobarRepo.findAll();
        if (userDetails.getKonobar().getRole().toLowerCase().equals("konobar")) return "redirect:sucelje";
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
        model.addAttribute("userDetails", konobarDetails);
        model.addAttribute("konobar", konobar);
        return "edit_users";
    }

    @PostMapping("users/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Konobar konobar, BindingResult result, Model model) {

        Optional<Konobar> konobarEditOptional = konobarRepo.findById(id);

        if (konobarEditOptional.isPresent()) {
            Konobar konobarEdit = konobarEditOptional.get();
            if (konobar.getPassword().equals(konobarEdit.getPassword())) {
                konobarEdit.setEmail(konobar.getEmail());
                konobarEdit.setFirstname(konobar.getFirstname());
                konobarEdit.setLastname(konobar.getLastname());
                konobarRepo.save(konobarEdit);
            } else {
                if (result.hasErrors()) {
                    konobar.setId(id);
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
                    model.addAttribute("activeLink", "Konobar");
                    model.addAttribute("userDetails", konobarDetails);
                    model.addAttribute("konobar", konobar);
                    return "edit_users";
                } else  {
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String encodedPassword = passwordEncoder.encode(konobar.getPassword());
                    konobarEdit.setEmail(konobar.getEmail());
                    konobarEdit.setFirstname(konobar.getFirstname());
                    konobarEdit.setLastname(konobar.getLastname());
                    konobarEdit.setPassword(encodedPassword);
                    konobarEdit.setPasswordRepeat(encodedPassword);
                    konobarRepo.save(konobarEdit);
                }
            }
        }
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Konobar konobar = konobarRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid konobar Id:" + id));
        konobarRepo.delete(konobar);
        return "redirect:/users";
    }
}