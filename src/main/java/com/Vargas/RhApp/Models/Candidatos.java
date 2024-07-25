package com.Vargas.RhApp.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Candidatos {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String rg;

    @NotEmpty
    private String nameCandidato;

    @NotEmpty
    private String email;

    @ManyToOne
    private Vaga vaga;


    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameCandidato() {
        return nameCandidato;
    }

    public void setNameCandidato(String nameCandidato) {
        this.nameCandidato = nameCandidato;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
