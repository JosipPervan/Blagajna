package ba.sum.fsre.blagajna.model;

import ba.sum.fsre.blagajna.repositories.ProizvodiNaRacunuRepository;
import ba.sum.fsre.blagajna.repositories.ProizvodiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ba.sum.fsre.blagajna.model.Racun;
import ba.sum.fsre.blagajna.model.ProizvodiNaRacunu;

import java.util.List;

@Service
public class RacunUpravljanje {

    @Autowired
    private ProizvodiNaRacunuRepository proizvodiNaRacunuRepo;

    @Autowired
    private ProizvodiRepository proizvodiRepo;

}
