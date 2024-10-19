package br.com.fiap.fiaptechchallengefase4.infra.config;

import br.com.fiap.fiaptechchallengefase4.core.gateway.ProdutoGateway;
import br.com.fiap.fiaptechchallengefase4.gateway.repository.ProdutoRepository;
import br.com.fiap.fiaptechchallengefase4.core.usecases.ProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ProdutoUseCase produtoUseCase(ProdutoGateway produtoGateway) {
        return new ProdutoUseCase(produtoGateway);
    }

}
