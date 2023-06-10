package ba.sum.fsre.blagajna.controller;

import ba.sum.fsre.blagajna.model.Kategorija;
import ba.sum.fsre.blagajna.model.KonobarDetails;
import ba.sum.fsre.blagajna.model.Proizvodi;
import ba.sum.fsre.blagajna.repositories.KategorijeRepository;
import ba.sum.fsre.blagajna.repositories.ProizvodiRepository;
import jakarta.persistence.EntityNotFoundException;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Kategorija> k = kategorijeRepo.findById(Long.valueOf(1));
        model.addAttribute("listKategorije", kategorijeRepo.findAll());
        model.addAttribute("selectedKategorija", k.get());
        // Napravi racun i prosljedi kao varijablu
        return "sucelje";
    }



    @GetMapping("/sucelje/{id}")
    public String getKategorijaById(@PathVariable("id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
        model.addAttribute("userDetails", konobarDetails);
        model.addAttribute("activeLink", "Sucelje");
        Optional<Kategorija> k = kategorijeRepo.findById(id);
        model.addAttribute("listKategorije", kategorijeRepo.findAll());
        model.addAttribute("selectedKategorija", k.get());
        // Dohvati racun i prosljedi kao varijablu
        return "sucelje";
    }



}