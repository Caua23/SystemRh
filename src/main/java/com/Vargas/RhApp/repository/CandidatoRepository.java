package com.Vargas.RhApp.repository;
import com.Vargas.RhApp.Models.Vaga;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.Vargas.RhApp.Models.Candidatos;
public interface CandidatoRepository extends CrudRepository< Candidatos, String> {

    Iterable<Candidatos>findByVaga(Vaga vaga);

    Candidatos findByRg(String rg);

    Candidatos findById(long id);

    List<Candidatos>findByNameCandidato(String nameCandidato);

}
