package jpwp.jpwpproject.model;

import jpwp.jpwpproject.util.Language;
import jpwp.jpwpproject.util.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private LocalDate registrationDate;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean locked = false;

    private Boolean enabled = false;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Language.class)
    private Set<Language> secondLanguage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KNOWN_TRANSLATION_ID")
    private KnownTranslations knownTranslations;

    public User() {
    }

    public User(String username, String password, LocalDate registrationDate, String email, Set<Language> secondLanguage, KnownTranslations knownTranslations) {
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
        this.email = email;
        this.role = Role.USER;
        this.locked = false;
        this.enabled = false;
        this.secondLanguage = secondLanguage;
        this.knownTranslations = knownTranslations;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Language> getSecondLanguage() {
        return secondLanguage;
    }

    public void setSecondLanguage(Set<Language> secondLanguage) {
        this.secondLanguage = secondLanguage;
    }

    public KnownTranslations getKnownTranslations() {
        return knownTranslations;
    }

    public void setKnownTranslations(KnownTranslations knownTranslations) {
        this.knownTranslations = knownTranslations;
    }
}
