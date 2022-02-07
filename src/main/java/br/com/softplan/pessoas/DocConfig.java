package br.com.softplan.pessoas;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocConfig {

    
    @Bean
    public GroupedOpenApi allOpenApi() {
        String packagesToscan[] = {"br.com.softplan.pessoas.control", "br.com.softplan.pessoas.v1.control","br.com.softplan.pessoas.v2.control"};
        return GroupedOpenApi.builder().group("Todas as versões").packagesToScan(packagesToscan)
        .build();
    }
    
    @Bean
    public GroupedOpenApi v1OpenApi() {
        String paths[] = { "/v1/**" };
        return GroupedOpenApi.builder().group("v1").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi v2OpenApi() {
        String paths[] = { "/v2/**" };
        return GroupedOpenApi.builder().group("v2").pathsToMatch(paths)
                .build();
    }

}
