package com.Vargas.RhApp.repository;

import com.Vargas.RhApp.Models.Dependentes;
import com.Vargas.RhApp.Models.Funcionario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DependentesRepository extends CrudRepository<Dependentes, String> {


    Iterable<Dependentes> findByFuncionario(Funcionario funcionario);


    Dependentes findById(long id);

    Dependentes findByCpf(String cpf);

    List<Dependentes> findByName(String name);

}
