package ba.sum.fsre.blagajna.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
@Entity
@Table(name="kategorija")
public class Kategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 200, nullable = false)
    @NotBlank(message = "Unesite naziv kategorije.")
    String naziv;

    @OneToMany(mappedBy="kategorija")
    private Set<Proizvodi> proizvodi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<Proizvodi> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(Set<Proizvodi> proizvodi) {
        this.proizvodi = proizvodi;
    }
}
