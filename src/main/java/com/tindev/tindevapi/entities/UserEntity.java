package com.tindev.tindevapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tindev.tindevapi.enums.Gender;
import com.tindev.tindevapi.enums.Pref;
import com.tindev.tindevapi.enums.ProgLangs;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tindev_user")
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "user_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "personinfo_id", insertable = false, updatable = false)
    private Integer PersoInfoId;

    @Column(name = "address_id", insertable = false, updatable = false)
    private Integer AddressId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "proglangs")
    @Enumerated(EnumType.STRING)
    private ProgLangs progLangs;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "pref")
    @Enumerated(EnumType.STRING)
    private Pref pref;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personinfo_id")
    private PersonInfoEntity personInfoEntity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @JsonIgnore
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> likes;

    @JsonIgnore
    @OneToMany(mappedBy = "userEntityLiked", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> likesReceived;

    @OneToMany(mappedBy = "userEntityFirst", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchEntity> matches;

    @OneToMany(mappedBy = "userEntitySecond", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchEntity> matchesSecond;

    @ManyToOne()
    @JoinColumn(name = "role")
    private RoleEntity role;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> rule = new ArrayList<>();
        rule.add(role);
        return rule;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}
