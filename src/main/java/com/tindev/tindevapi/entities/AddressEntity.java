package com.tindev.tindevapi.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "address")
public class AddressEntity {

    @Id
    @Column(name = "id_address", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAddress;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private long number;

    @Column(name = "city")
    private String city;

    @Column(name = "cep")
    private String cep;

}
