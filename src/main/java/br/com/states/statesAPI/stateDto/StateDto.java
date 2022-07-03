package br.com.states.statesAPI.stateDto;

import br.com.states.statesAPI.entity.State;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class StateDto {
        private String nome;
        private String regiao;
        private Integer populacao;
        private String capital;
        private Double area;

        public StateDto(State state) {
                this.nome = state.getNome();
                this.regiao = state.getRegiao();
                this.populacao = state.getPopulacao();
                this.capital = state.getCapital();
                this.area = state.getArea();
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getRegiao() {
                return regiao;
        }

        public void setRegiao(String regiao) {
                this.regiao = regiao;
        }

        public Integer getPopulacao() {
                return populacao;
        }

        public void setPopulacao(Integer populacao) {
                this.populacao = populacao;
        }

        public String getCapital() {
                return capital;
        }

        public void setCapital(String capital) {
                this.capital = capital;
        }

        public Double getArea() {
                return area;
        }

        public void setArea(Double area) {
                this.area = area;
        }

        public static Page<StateDto> toList(Page<State> state) {
                return state.map(StateDto :: new);
        }

        public StateDto() {
        }
}
