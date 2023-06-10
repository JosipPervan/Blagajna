package ba.sum.fsre.blagajna.controller;

import ba.sum.fsre.blagajna.model.*;
import ba.sum.fsre.blagajna.repositories.KategorijeRepository;
import ba.sum.fsre.blagajna.repositories.ProizvodiRepository;
import ba.sum.fsre.blagajna.repositories.RacunProizvodiRepo;
import ba.sum.fsre.blagajna.repositories.RacunRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
import java.util.Set;

@Controller
public class SuceljeController {

    @Autowired
    KategorijeRepository kategorijeRepo;
    @Autowired
    RacunRepository racunRepo;

    @Autowired
    RacunProizvodiRepo racunProizvodiRepo;
    @Autowired
    ProizvodiRepository proizvodRepo;

    @GetMapping("/sucelje")
    public String listSucelje() {
        Racun racun = new Racun();
        racun = racunRepo.save(racun);
        // Napravi racun i prosljedi kao varijablu
        return "redirect:sucelje/1/"+String.valueOf(racun.getId());
    }

    @GetMapping("/sucelje/{id}/{racun_id}")
    public String getKategorijaById(@PathVariable("id") Long id, @PathVariable("racun_id") Long racun_id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
        model.addAttribute("userDetails", konobarDetails);
        model.addAttribute("activeLink", "Sucelje");
        Optional<Kategorija> k = kategorijeRepo.findById(id);
        Optional<Racun> r = racunRepo.findById(racun_id);
        model.addAttribute("listKategorije", kategorijeRepo.findAll());
        model.addAttribute("selectedKategorija", k.get());
        model.addAttribute("selectedRacun", r.get());
        // Dohvati racun i prosljedi kao varijablu
        return "sucelje";
    }

    @Transactional
    @GetMapping("/sucelje_add/{racun_id}/{proizvod_id}")
    public String addProizvode(@PathVariable("racun_id") Long racun_id, @PathVariable("proizvod_id") Long proizvod_id, Model model){
        Optional<Proizvodi> p = proizvodRepo.findById(proizvod_id);
        Optional<Racun> r = racunRepo.findById(racun_id);
        Proizvodi pr = p.get();
        Racun ra = r.get();
        RacunProizvod racunProizvod = new RacunProizvod();
        racunProizvod.setRacun(ra);
        racunProizvod.setProizvod(pr);
        List<RacunProizvod> racunProizvodList = ra.getRacunProizvodi();
        racunProizvodList.add(racunProizvod);
        ra.setRacunProizvodi(racunProizvodList);
        racunRepo.save(ra);
        return "redirect:/sucelje/1/"+String.valueOf(ra.getId());
    }

}