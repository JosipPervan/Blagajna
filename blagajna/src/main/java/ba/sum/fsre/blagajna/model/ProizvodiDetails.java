package ba.sum.fsre.blagajna.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ProizvodiDetails {
    private Proizvodi proizvodi;

    public ProizvodiDetails(Proizvodi proizvodi) {
        this.proizvodi = proizvodi;
    }

    public String getNaziv() {
        return proizvodi.naziv;
    }

    public String getCijena(){
        return proizvodi.cijena;
    }

    public Proizvodi getProizvod() {
        return proizvodi;
    }

    public void setProizvodi(Proizvodi proizvodi) {
        this.proizvodi = proizvodi;
    }

}