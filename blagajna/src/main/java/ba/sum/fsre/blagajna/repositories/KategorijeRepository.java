package ba.sum.fsre.blagajna.repositories;
import ba.sum.fsre.blagajna.model.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface KategorijeRepository extends JpaRepository<Kategorija, Long> {

        Optional<Kategorija> findById(Long id);

}