package ba.sum.fsre.blagajna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RacunProizvodId implements Serializable {
    @Column(name = "racun_id")
    private Long racunId;

    @Column(name = "proizvod_id")
    private Long proizvodId;

    public RacunProizvodId(Long racunId, Long proizvodId) {
        this.racunId = racunId;
        this.proizvodId = proizvodId;
    }

    public RacunProizvodId() {

    }


}