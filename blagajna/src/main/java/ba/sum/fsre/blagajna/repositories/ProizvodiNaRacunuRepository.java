package ba.sum.fsre.blagajna.repositories;

import ba.sum.fsre.blagajna.model.Konobar;
import ba.sum.fsre.blagajna.model.Proizvodi;
import ba.sum.fsre.blagajna.model.ProizvodiNaRacunu;
import ba.sum.fsre.blagajna.model.Racun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProizvodiNaRacunuRepository extends JpaRepository<ProizvodiNaRacunu, Long> {

        public List<ProizvodiNaRacunu> findByRacun(Racun racun);

        public Optional<ProizvodiNaRacunu> findByProizvodiAndRacun(Racun racun, Proizvodi proizvodi);

        @Query("SELECT pnr FROM ProizvodiNaRacunu pnr WHERE pnr.racun.id = ?1 AND pnr.proizvodi.id= ?2")
        public Optional<ProizvodiNaRacunu> findByProizvodiAndRacun(Long racunId, Long proizvodId);

        @Query("SELECT pnr FROM ProizvodiNaRacunu pnr WHERE pnr.racun.id = ?1")
        public List<ProizvodiNaRacunu> findByRacun(Long racunId);

}
