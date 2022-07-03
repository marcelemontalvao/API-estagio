package br.com.states.statesAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class State implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "regiao", nullable = false)
    private String regiao;

    @Column(name = "populacao", nullable = false)
    private Integer populacao;

    @Column(name = "capital", nullable = false)
    private String capital;

    @Column(name = "area", nullable = false)
    private Double area;
}
