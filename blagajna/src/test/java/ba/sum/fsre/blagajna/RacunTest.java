package ba.sum.fsre.blagajna;

import ba.sum.fsre.blagajna.model.Proizvodi;
import ba.sum.fsre.blagajna.model.ProizvodiNaRacunu;
import ba.sum.fsre.blagajna.model.Racun;
import ba.sum.fsre.blagajna.repositories.ProizvodiNaRacunuRepository;
import com.fasterxml.jackson.databind.JsonSerializer;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RacunTest {

    @Autowired
    private ProizvodiNaRacunuRepository proizvodiNaRacunuRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testDodajJedanProizvod(){

        Proizvodi proizvodi = entityManager.find(Proizvodi.class, 2);
        Racun racun = entityManager.find(Racun.class, 1);
        ProizvodiNaRacunu novi = new ProizvodiNaRacunu();
        novi.setProizvodi(proizvodi);
        novi.setRacun(racun);
        novi.setKolicina(5);

        ProizvodiNaRacunu savedProizvodiNaRacunu = proizvodiNaRacunuRepo.save(novi);

        assertTrue(savedProizvodiNaRacunu.getId() > 0);
    }

}
