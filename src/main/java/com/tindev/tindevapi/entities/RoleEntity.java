package com.tindev.tindevapi.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "ROLE")
public class RoleEntity implements Serializable, GrantedAuthority {

    @Id
    @Column(name = "id_role", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String name;


    @Override
    public String getAuthority() {
        return name;
    }
}
