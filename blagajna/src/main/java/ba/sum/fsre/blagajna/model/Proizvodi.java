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

    @Column(length = 50, nullable = false)
    @NotNull(message = "Molimo unesite cijenu proizvoda.")
    Double cijena;

    @ManyToOne
    @JoinColumn(name="kategorija_id", nullable=false)
    @NotNull(message = "Molimo unesite cijenu proizvoda.")
    private Kategorija kategorija;

    @Column(length = 200, nullable = true)
    private String slika;

    public Proizvodi(){}
    public Proizvodi(Long id, String naziv, Double cijena, Kategorija kategorija, String slika) {
        this.id = id;
        this.naziv = naziv;
        this.cijena = cijena;
        this.kategorija = kategorija;
        this.slika = slika;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
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

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }
}


