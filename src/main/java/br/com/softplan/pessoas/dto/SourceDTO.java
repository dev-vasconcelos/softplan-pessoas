package br.com.softplan.pessoas.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SourceDTO {

    private String javaGit = "http://github.com/dev-vasconcelos/softplan-pessoas-java";
    private String dotnetGit = "http://github.com/dev-vasconcelos/-softplan-pessoas-dotnet";

    private String front = "http://github.com/dev-vasconcelos/softplan-pessoas-front";

    private String dockerHubJavaComFront = "http://dockerhub.com/dev-vasconcelos/softplan-pessoas-java-front";
    private String dockerHubDotnetComFront = "http://dockerhub.com/dev-vasconcelos/softplan-pessoas-dotnet-front";

    private String dockerHubJavaSemFront = "http://dockerhub.com/dev-vasconcelos/softplan-pessoas-java-sem-front";
    private String dockerHubDotnetSemFront = "http://dockerhub.com/dev-vasconcelos/softplan-pessoas-dotnet-sem-front";

    private List<ProjetoDockerComposeDTO> projetosDockerCompose;

    public SourceDTO() {
        List<String> composeJavaUrls = new ArrayList<String>();
        composeJavaUrls.add("htttps://dockerhub.com/imagem_java");
        composeJavaUrls.add("htttps://dockerhub.com/imagem_front");
        ProjetoDockerComposeDTO projetoComposeJava = new ProjetoDockerComposeDTO(
                "Controle de Pessoas docker-compose utilizando Java Spring",
                "Rodar front e back individualmente a partir de um docker-compose", composeJavaUrls,
                "docker-compose run bla bla");

        List<String> composeDotnetUrls = new ArrayList<String>();
        composeDotnetUrls.add("https://dockerhub.com/imagem_dotnet");
        composeDotnetUrls.add("https://dockerhub.com/imagem_front");
        ProjetoDockerComposeDTO projetoComposeDotnet = new ProjetoDockerComposeDTO(
                "Controle de Pessoas docker-compose utilizando dotnet",
                "Rodar front e back individualmente a partir de um docker-compose", composeDotnetUrls,
                "docker-compose run bla bla");

        projetosDockerCompose = new ArrayList<ProjetoDockerComposeDTO>();
        projetosDockerCompose.add(projetoComposeJava);
        projetosDockerCompose.add(projetoComposeDotnet);
    }

}
