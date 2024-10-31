package com.Vargas.RhApp.repository;

import com.Vargas.RhApp.Models.Funcionario;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<Funcionario, String> {

    Funcionario findById(long id);
    Funcionario findByName(String name);


}
