package ba.sum.fsre.blagajna.model;

import jakarta.persistence.*;

@Entity
@Table(name = "proizvodi_na_racunu")
public class ProizvodiNaRacunu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proizvodi_id")
    private Proizvodi proizvodi;

    @ManyToOne
    @JoinColumn(name = "racun_id")
    private Racun racun;

    private Long kolicina;

    public  ProizvodiNaRacunu(){

    }

    public ProizvodiNaRacunu(Long id, Proizvodi proizvodi, Racun racun, Long kolicina) {
        this.id = id;
        this.proizvodi = proizvodi;
        this.racun = racun;
        this.kolicina = kolicina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proizvodi getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(Proizvodi proizvodi) {
        this.proizvodi = proizvodi;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public long getKolicina() {
        return kolicina;
    }

    public void setKolicina(long kolicina) {
        this.kolicina = kolicina;
    }
}
