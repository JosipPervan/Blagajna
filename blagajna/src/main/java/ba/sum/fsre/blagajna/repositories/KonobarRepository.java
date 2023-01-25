package ba.sum.fsre.blagajna.repositories;

import ba.sum.fsre.blagajna.model.Konobar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KonobarRepository extends
        JpaRepository<Konobar, Long> {

    @Query("SELECT u FROM Konobar u WHERE u.email = ?1")
    public Konobar findByEmail(String email);
}