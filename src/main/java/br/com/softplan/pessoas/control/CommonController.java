package br.com.softplan.pessoas.control;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.softplan.pessoas.dto.SourceDTO;

@Controller
@RequestMapping("/source")
public class CommonController {

    @GetMapping()
    public ResponseEntity<?> getUrl() {
        SourceDTO source = new SourceDTO();
        return ResponseEntity.ok(source);
    }

}
