package ba.sum.fsre.blagajna.repositories;

import ba.sum.fsre.blagajna.model.Kategorija;
import ba.sum.fsre.blagajna.model.Proizvodi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KategorijeRepository extends JpaRepository<Kategorija, Long> {

        Optional<Kategorija> findById(Long id);

}