package ba.sum.fsre.blagajna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "racun_proizvod")
public class RacunProizvod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("racun_id")
    private Racun racun;

    @ManyToOne
    @MapsId("proizvod_id")
    private Proizvodi proizvod;

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public Proizvodi getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvodi proizvod) {
        this.proizvod = proizvod;
    }



    public RacunProizvod(Racun racun, Proizvodi proizvod) {
        this.racun = racun;
        this.proizvod = proizvod;
    }

    public RacunProizvod() {

    }
}

