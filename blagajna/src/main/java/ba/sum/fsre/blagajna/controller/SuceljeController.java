package ba.sum.fsre.blagajna.controller;

import ba.sum.fsre.blagajna.model.KonobarDetails;
import ba.sum.fsre.blagajna.model.Proizvodi;
import ba.sum.fsre.blagajna.repositories.ProizvodiRepository;
import ba.sum.fsre.blagajna.repositories.SuceljeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class SuceljeController {

    @Autowired
    SuceljeRepository suceljeRepo;

    @GetMapping("/sucelje")
    public String listSucelje(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
        model.addAttribute("userDetails", konobarDetails);
        model.addAttribute("activeLink", "Sucelje");
        return "sucelje";
    }
}