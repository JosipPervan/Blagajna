package ba.sum.fsre.blagajna.controller;

import ba.sum.fsre.blagajna.model.*;
import ba.sum.fsre.blagajna.repositories.KategorijeRepository;
import ba.sum.fsre.blagajna.repositories.ProizvodiNaRacunuRepository;
import ba.sum.fsre.blagajna.repositories.ProizvodiRepository;
import ba.sum.fsre.blagajna.repositories.RacunRepository;
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

    @Autowired
    ProizvodiNaRacunuRepository proizvodiNaRacunuRepo;

    @Autowired
    RacunRepository racunRepo;

    @GetMapping("/sucelje")
    public String listSucelje(Model model) {
        Racun racun = new Racun();
        racun = racunRepo.save(racun);
        model.addAttribute("newIdRacuna", new Racun());
        return "redirect:sucelje/1/"+String.valueOf(racun.getId());
    }



    @GetMapping("/sucelje/{id}/{id_racun}")
    public String getKategorijaById(@PathVariable("id") Long id, @PathVariable("id_racun") Long idRacun, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
        model.addAttribute("userDetails", konobarDetails);
        model.addAttribute("activeLink", "Sucelje");
        Optional<Kategorija> k = kategorijeRepo.findById(id);
        Optional<Racun> r = racunRepo.findById(idRacun);
        model.addAttribute("listKategorije", kategorijeRepo.findAll());
        model.addAttribute("selectedKategorija", k.get());
        model.addAttribute("selectedRacun", r.get());
        model.addAttribute("proizvodiNaRacunu", proizvodiNaRacunuRepo.findByRacun(idRacun));

        double cijena = 0.0f;
        for (ProizvodiNaRacunu pnr : proizvodiNaRacunuRepo.findByRacun(idRacun))
            cijena = cijena + (pnr.getKolicina() * pnr.getProizvodi().getCijena());

        model.addAttribute("ukupnaCijena", cijena);
        // Dohvati racun i prosljedi kao varijablu
        return "sucelje";
    }


}