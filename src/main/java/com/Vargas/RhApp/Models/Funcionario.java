package com.Vargas.RhApp.Models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
@Entity
public class Funcionario implements Serializable {

    private static final long serialversionUUId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String data;
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.REMOVE)
    private List<Dependentes>dependentes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Dependentes> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependentes> dependentes) {
        this.dependentes = dependentes;
    }
}
