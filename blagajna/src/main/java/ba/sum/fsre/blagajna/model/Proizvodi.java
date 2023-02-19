package ba.sum.fsre.blagajna.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="proizvodi")
public class Proizvodi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 200, nullable = false)
    @NotBlank(message = "Unesite naziv proizvoda.")
    String naziv;

    @Column(length = 200, nullable = false)
    @NotNull(message = "Unesite naziv kategorije.")
    @Enumerated(EnumType.STRING)
    private Kategorija kategorija;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Molimo unesite cijenu proizvoda.")
    Double cijena;

    public Proizvodi(){}
    public Proizvodi(Long id, String naziv, Kategorija kategorija, Double cijena) {
        this.id = id;
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.cijena = cijena;
    }

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

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }


}


