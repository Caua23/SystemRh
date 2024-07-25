package com.Vargas.RhApp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.Vargas.RhApp.Models.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, String>{
    Vaga findByCodigo(long codigo);

    List<Vaga> findByNome(String nome);;
}
