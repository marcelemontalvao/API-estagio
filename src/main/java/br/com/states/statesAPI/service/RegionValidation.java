package br.com.states.statesAPI.service;

import br.com.states.statesAPI.exception.RegionInvalidException;
import br.com.states.statesAPI.stateDto.StateDto;
import org.springframework.stereotype.Service;

@Service
public class RegionValidation {
    public void validate(StateDto stateDto) throws RegionInvalidException {
        if (stateDto.getRegiao().equalsIgnoreCase("Norte")) {

        }
        else if (stateDto.getRegiao().equalsIgnoreCase("Centro-oeste")) {

        }
        else if (stateDto.getRegiao().equalsIgnoreCase("Sudeste")) {

        }
        else if  (stateDto.getRegiao().equalsIgnoreCase("Sul")) {

        }
        else if (stateDto.getRegiao().equalsIgnoreCase("Nordeste")) {

        }
        else throw new RegionInvalidException("Insira uma região válida");
    }
}
