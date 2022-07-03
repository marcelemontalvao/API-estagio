package br.com.states.statesAPI.service;

import br.com.states.statesAPI.entity.State;
import br.com.states.statesAPI.exception.RegionInvalidException;
import br.com.states.statesAPI.repository.StateRepository;
import br.com.states.statesAPI.stateDto.StateDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RegionValidation regionValidation;

    public State register(StateDto stateDto) throws RegionInvalidException {
        State state = modelMapper.map(stateDto, State.class);
        regionValidation.validate(stateDto);
        stateRepository.save(state);
        return state;
    }

    public State save(State state) {
        return stateRepository.save(state);
    }

    public State updateState(StateDto stateDto, Long id) {
        return searchId(id)
                .map(stateBase -> {
                    modelMapper.map(stateDto, stateBase);
                    stateBase.setId(id);
                    save(stateBase);
                    return stateBase;
                }).orElse(null);
    }
    public List<State> findAll() {
        List<State> state = stateRepository.findAll();
        return state;
    }
    public Optional<State> searchId(Long id) {
        return stateRepository.findById(id);
    }
    public State delete(Long id){
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado Não Encontrado" + id));
        stateRepository.delete(state);
        return state;
    }

}
