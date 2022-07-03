package br.com.states.statesAPI.controller;

import br.com.states.statesAPI.entity.State;
import br.com.states.statesAPI.exception.RegionInvalidException;
import br.com.states.statesAPI.repository.StateRepository;
import br.com.states.statesAPI.service.RegionValidation;
import br.com.states.statesAPI.service.StateService;
import br.com.states.statesAPI.stateDto.StateDto;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/states")
public class ApiController {
    @Autowired
    private StateService stateService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StateRepository stateRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State register(@RequestBody StateDto stateDto) throws RegionInvalidException {
        return stateService.register(stateDto);
    }

   /* @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<State> findAll(){
        return stateService.findAll();
    }
*/
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StateDto> filterState(@RequestParam (required = false) String regiao, @PageableDefault (sort = "id", direction = Sort.Direction.ASC) Pageable sort) {
        if (regiao == null) {
            Page<State> state = stateRepository.findAll(sort);
            return StateDto.toList(state);
        }else {
            Page<State> state = stateRepository.findByRegiao(regiao, sort);
            return StateDto.toList(state);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public State searchStateById(@PathVariable("id") Long id) {
        return stateService.searchId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado não encontrado."));
    }

   /* @GetMapping("/ordenarRegiao")
    public List<State> orderRegion(){
        return stateService.findByRegiaoOrderByRegiao();
    }

   @GetMapping("/ordenarPopulacao")
    public List<State> orderPopulation(){
        return stateService.findByPopulacaoOrderByPopulacaoDesc();
    }
    @GetMapping("/ordenarArea")
    public List<State> orderArea(){
        return stateService.findByAreaOrderByAreaDesc();
    }*/

   @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putState(@PathVariable("id") Long id, @RequestBody StateDto stateDto) {
        var state = stateService.updateState(stateDto, id);
        if (state == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado não encontrado.");
        }
   }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        stateService.searchId(id)
                        .map(state -> {
                            stateService.delete(state.getId());
                            return Void.TYPE;
                        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado não encontrado."));
    }
}
