package ba.sum.fsre.blagajna.controller;

import ba.sum.fsre.blagajna.model.Konobar;
import ba.sum.fsre.blagajna.repositories.KonobarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private KonobarRepository konobarRepo;

    @GetMapping("/")
    public String index (Model model) {
        return "redirect:users";
    }
    @GetMapping("/register")
    public String showRegistrationForm (Model model) {
        model.addAttribute("konobar", new Konobar());
        return "register_form";
    }

    @PostMapping("/register_user")
    public String registerUser (@Valid Konobar konobar, BindingResult result, Model model) {
        boolean errors = result.hasErrors();
        if (errors) {
            model.addAttribute("konobar", konobar);
            return "register_form";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(konobar.getPassword());
        konobar.setPasswordRepeat(encodedPassword);
        konobar.setPassword(encodedPassword);
        konobarRepo.save(konobar);
        return "register_success";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("konobar", new Konobar());
        return "login_form";
    }
}