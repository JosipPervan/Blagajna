package ba.sum.fsre.blagajna.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

import java.util.Currency;

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

    @Column(precision = 10, scale = 2, nullable = false)
    @NotNull(message = "Molimo unesite cijenu proizvoda.")
    BigDecimal cijena;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Molimo unesite valutu.")
    String valuta;



    public Proizvodi(){}
    public Proizvodi(Long id, String naziv, Kategorija kategorija, BigDecimal cijena) {
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

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proizvodi proizvodi = (Proizvodi) o;
        return Objects.equals(id, proizvodi.id) && Objects.equals(naziv, proizvodi.naziv) && Objects.equals(kategorija, proizvodi.kategorija) && Objects.equals(cijena, proizvodi.cijena) && Objects.equals(valuta, proizvodi.valuta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, kategorija, cijena, valuta);
    }

    @Override
    public String toString() {
        return "Proizvodi{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", kategorija='" + kategorija + '\'' +
                ", cena=" + cijena +
                ", valuta='" + valuta + '\'' +
                '}';
    }

}



