package ba.sum.fsre.blagajna.controller;

import ba.sum.fsre.blagajna.model.KonobarDetails;
import ba.sum.fsre.blagajna.model.Proizvodi;
import ba.sum.fsre.blagajna.repositories.KategorijeRepository;
import ba.sum.fsre.blagajna.repositories.ProizvodiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SuceljeController {

    @Autowired
    KategorijeRepository kategorijeRepo;

    @GetMapping("/sucelje")
    public String listSucelje(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
        model.addAttribute("userDetails", konobarDetails);
        model.addAttribute("activeLink", "Sucelje");
        model.addAttribute("listKategorije", kategorijeRepo.findAll());
        return "sucelje";
    }
}