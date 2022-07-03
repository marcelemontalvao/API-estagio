package br.com.states.statesAPI.repository;

import br.com.states.statesAPI.entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{
    Page<State> findByRegiao(String regiao, Pageable sort);
}
