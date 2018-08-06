package br.com.raissasouza.raissasouza.repository;

import br.com.raissasouza.raissasouza.model.Jogador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JogadorRepository extends CrudRepository<Jogador, String> {

    List<Jogador> findByNome(String nome);
    List<Jogador> findByEmail(String email);
    List<Jogador> findByGrupo(String grupo);
}
