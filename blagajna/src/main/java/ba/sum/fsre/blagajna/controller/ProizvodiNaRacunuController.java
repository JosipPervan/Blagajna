package ba.sum.fsre.blagajna.controller;


import ba.sum.fsre.blagajna.model.*;
import ba.sum.fsre.blagajna.repositories.ProizvodiNaRacunuRepository;
import ba.sum.fsre.blagajna.repositories.ProizvodiRepository;
import ba.sum.fsre.blagajna.repositories.RacunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;

@Controller
public class ProizvodiNaRacunuController {

    @Autowired
    RacunRepository racunRepo;

    @Autowired
    ProizvodiRepository proizvodiRepo;
    @Autowired
    ProizvodiNaRacunuRepository proizvodiNaRacunuRepo;
    @GetMapping("/racun/dodaj/{id_racuna}/{id_proizvoda}")
    public String dodajProizvod(@PathVariable("id_racuna") Long idRacuna, @PathVariable("id_proizvoda") Long idProizvoda, Model model) {
        Optional<Proizvodi> p = proizvodiRepo.findById(idProizvoda);
        Optional<Racun> r = racunRepo.findById(idRacuna);

        Optional<ProizvodiNaRacunu> pnr = proizvodiNaRacunuRepo.findByProizvodiAndRacun(r.get().getId(), p.get().getId());
        if (pnr.isPresent()) {
            ProizvodiNaRacunu item = pnr.get();
            item.setKolicina(item.getKolicina() + 1);
            proizvodiNaRacunuRepo.save(item);
        } else {
            ProizvodiNaRacunu item = new ProizvodiNaRacunu();
            item.setProizvodi(p.get());
            item.setRacun(r.get());
            item.setKolicina(1);
            proizvodiNaRacunuRepo.save(item);
        }
        return "redirect:/sucelje/1/"+String.valueOf(idRacuna);
    }



    }
