package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testes")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(String nome) {
        return cozinhaRepository.findTodasByNome(nome);
    }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> cozinhasUnicaPorNome(String nome) {
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/cozinhas/like")
    public List<Cozinha> cozinhasLikePorNome(String nome) {
        return cozinhaRepository.findTodasByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restauraurantesTaxaFrete(
            BigDecimal taxaInicial, BigDecimal taxaFinal
    ) {
        return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nome-cozinha")
    public List<Restaurante> restauraurantesNomeCozinha(
            String nome, Long cozinhaId
    ) {
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
    }

    @GetMapping("/restaurantes/por-nome-cozinha-query")
    public List<Restaurante> restauraurantesNomeCozinhaQuery(
            String nome, Long cozinhaId
    ) {
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }

    @GetMapping("/restaurantes/primeiro")
    public Optional<Restaurante> restauraurantesNomeCozinha(String nome) {
        return restauranteRepository.findFirstQualquerNomeByNomeContaining(nome);
    }

    // testar

    @GetMapping("/cozinha/exists")
    public boolean cozinhasExists(String nome) {
        return cozinhaRepository.existsByNome(nome);
    }


    @GetMapping("/restaurantes/top2")
    public List<Restaurante> restauraurantesTop2(String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }


    @GetMapping("/restaurantes/restaurantes-por-cozinhas")
    public int restaurantesCountCozinhas(Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }


    @GetMapping("/restaurantes/restaurantes-por-nome-taxa-frete")
    public List<Restaurante> restaurantesPorNomeTaxaFrete(
            String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal
    ) {
        return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        return restauranteRepository.findComFreteGratis(nome);
    }

    @GetMapping("/restaurantes/primeiro-encomtrado")
    public Optional<Restaurante> restaurantePrimeiro() {
        return restauranteRepository.buscarPrimeiro();
    }

    @GetMapping("/cozinhas/primeira-encomtrado")
    public Optional<Cozinha> cozinhaPrimeiro() {
        return cozinhaRepository.buscarPrimeiro();
    }



}
