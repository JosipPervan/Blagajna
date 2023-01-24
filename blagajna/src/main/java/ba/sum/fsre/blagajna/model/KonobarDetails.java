package ba.sum.fsre.blagajna.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class KonobarDetails implements org.springframework.security.core.userdetails.UserDetails {
    private Konobar konobar;

    public KonobarDetails(Konobar konobar) {
        this.konobar = konobar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return konobar.getPassword();
    }

    @Override
    public String getUsername() {
        return konobar.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return konobar.getFirstname() + " " + konobar.getLastname();
    }

    public Konobar getUser() {
        return konobar;
    }

    public void setUser(Konobar konobar) {
        this.konobar = konobar;
    }
}