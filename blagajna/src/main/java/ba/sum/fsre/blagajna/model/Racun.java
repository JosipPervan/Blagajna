package ba.sum.fsre.blagajna.model;


import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.*;

@Entity
@Table(name="racun")
public class Racun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Racun() {
    }

    public Racun(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "racun", cascade = CascadeType.ALL)
    private List<RacunProizvod> racunProizvodi;

    public List<RacunProizvod> getRacunProizvodi() {
        return racunProizvodi;
    }

    public void setRacunProizvodi(List<RacunProizvod> racunProizvodi) {
        this.racunProizvodi = racunProizvodi;
    }
}
