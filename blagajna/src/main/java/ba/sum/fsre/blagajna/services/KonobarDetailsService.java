package ba.sum.fsre.blagajna.services;


import ba.sum.fsre.blagajna.model.Konobar;
import ba.sum.fsre.blagajna.model.KonobarDetails;
import ba.sum.fsre.blagajna.repositories.KonobarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class KonobarDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private KonobarRepository konobarRepo;

    @Override
    public KonobarDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Konobar konobar = konobarRepo.findByEmail(username);
        if (konobar == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new KonobarDetails(konobar);
    }

}