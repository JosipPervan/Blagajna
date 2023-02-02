package ba.sum.fsre.blagajna.controller;

import ba.sum.fsre.blagajna.model.Proizvodi;
import ba.sum.fsre.blagajna.model.KonobarDetails;
import ba.sum.fsre.blagajna.repositories.ProizvodiRepository;
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

import java.util.List;

@Controller
public class ProizvodiController {

    @Autowired
    ProizvodiRepository proizvodiRepo;

    @GetMapping("proizvodi")
    public String listProizvodi (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
        model.addAttribute("konobarDetails", konobarDetails);
        List<Proizvodi> listProizvodi = proizvodiRepo.findAll();
        model.addAttribute("listProizvodi", listProizvodi);
        model.addAttribute("activeLink", "Proizvodi");
        return "proizvodi";
    }

    @GetMapping("proizvodi/add")
    public String showAddProizvodiForm (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
        model.addAttribute("activeLink", "Proizvodi");
        model.addAttribute("konobarDetails", konobarDetails);
        model.addAttribute("proizvodi", new Proizvodi());
        return "add_proizvodi";
    }

    @PostMapping("proizvodi/add")
    public String addProizvodi (@Valid Proizvodi proizvodi, BindingResult result, Model model) {
        boolean errors = result.hasErrors();
        if (errors) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
            model.addAttribute("activeLink", "Proizvodi");
            model.addAttribute("proizvodiDetails", konobarDetails);
            model.addAttribute("proizvodi", proizvodi);
            return "add_proizvodi";
        }
        proizvodiRepo.save(proizvodi);
        return "redirect:/proizvodi";
    }

    @GetMapping("proizvodi/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Proizvodi proizvodi = proizvodiRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid proizvodi Id:" + id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
        model.addAttribute("activeLink", "Proizvodi");
        model.addAttribute("konobarDetails", konobarDetails);
        model.addAttribute("proizvodi", proizvodi);
        return "edit_proizvodi";
    }

    @PostMapping("proizvodi/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Proizvodi proizvodi,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {proizvodi.setId(id);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            KonobarDetails konobarDetails = (KonobarDetails) authentication.getPrincipal();
            model.addAttribute("activeLink", "Proizvodi");
            model.addAttribute("konobarDetails", konobarDetails);
            model.addAttribute("proizvodi", proizvodi);
            return "edit_proizvodi";
        }

       proizvodiRepo.save(proizvodi);
        return "redirect:/proizvodi";
    }

    @GetMapping("/proizvodi/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Proizvodi proizvodi = proizvodiRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid proizvodi Id:" + id));
        proizvodiRepo.delete(proizvodi);
        return "redirect:/proizvodi";
    }
}