package br.com.softplan.pessoas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SourceDTO {

    private String javaGit = "https://github.com/dev-vasconcelos/softplan-pessoas";
    // private String dotnetGit = "http://github.com/dev-vasconcelos/softplan-pessoas-dotnet";

    private String front = "http://github.com/dev-vasconcelos/softplan-pessoas-front";

    private String dockerHubJavaComFront = "https://hub.docker.com/repository/docker/pedrobipede/softplan-pessoas";
    // private String dockerHubDotnetComFront = "https://hub.docker.com/repository/docker/pedrobipede/softplan-pessoas-dotnet";

    private String dockerHubJavaSemFront = "https://hub.docker.com/repository/docker/pedrobipede/softplan-pessoas-api";
    // private String dockerHubDotnetSemFront = "https://hub.docker.com/repository/docker/pedrobipede/softplan-pessoas-dotnet-api";

}
