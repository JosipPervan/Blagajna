package ba.sum.fsre.blagajna.repositories;

import ba.sum.fsre.blagajna.model.Kategorija;
import ba.sum.fsre.blagajna.model.Proizvodi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorijeRepository extends JpaRepository<Kategorija, Long> {
}