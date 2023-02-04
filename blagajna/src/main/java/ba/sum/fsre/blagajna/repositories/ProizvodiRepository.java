package ba.sum.fsre.blagajna.repositories;

import ba.sum.fsre.blagajna.model.Proizvodi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProizvodiRepository extends JpaRepository<Proizvodi, Long> {
}