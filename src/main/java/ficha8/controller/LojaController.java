package ficha8.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ficha8.dto.SimpleResponse;
import ficha8.dto.SimpleResponseLoja;
import ficha8.model.Loja;
import ficha8.service.LojaService;


@RestController
public class LojaController {
	
	private final LojaService lojaService;

	@Autowired
	public LojaController(LojaService lojaService) {
		this.lojaService = lojaService;
	}
	
	@PostMapping("/addLoja")
	public ResponseEntity<SimpleResponse> addLoja(@RequestBody Loja loja){
		SimpleResponseLoja srl = new SimpleResponseLoja();
		
		if(loja.getNome() == null || loja.getNome().isBlank()) {
			srl.setMessage("Nome da loja inválido.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		
		if(loja.getNumero_funcionarios() <= 0) {
			srl.setMessage("Número de funcionários inválido. Número menor ou igual do que 0.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		
		if(loja.getArea() < 1) {
			srl.setMessage("Numero de area inválido. Número menor do que 1.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		
		if(lojaService.addLoja(loja)) {
			srl.setAsSuccess("Uma nova loja foi inserida.");
			// srcc.setCentroComercial(centroComercialService.getAllCentroComercial());
		}else{
			srl.setAsError("Foi encontrado um erro ao inserir um.");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(srl);
	}
	
	@PostMapping("/addLoja/{loja_id}/Andar/{andar_id}")
	public String addLojaToAndar(@PathVariable String loja_id, @PathVariable String andar_id) {
		return lojaService.addLojaToAndar(loja_id, andar_id);
	}
	
	@DeleteMapping("/deleteLoja/{id}")
	public SimpleResponse deleteLoja(@PathVariable String id) {
		SimpleResponseLoja srl = new SimpleResponseLoja();
		
		if(lojaService.deleteLoja(id)) {
			srl.setAsSuccess("Uma loja foi apagada.");
			srl.setLojas(lojaService.getAllLoja());
		}else{
			srl.setAsError("Foi encontrado um erro ao apagar uma loja.");
		}
		
		return srl;
	}
	
	@PutMapping("/updateLoja")
    public SimpleResponse updateLoja(@RequestBody Loja l){
        SimpleResponseLoja srl = new SimpleResponseLoja();

        if (l.getId()==null){
            srl.setAsError("Id invalido");
            return srl;
        }

        if (l.getNome() == null || l.getNome().isBlank()){
            srl.setAsError("Nome da loja invalido");
            return srl;
        }
        
        if (l.getNumero_funcionarios() <= 0){
            srl.setAsError("Número de funcionários menor ou igual a 0");
            return srl;
        }
        
        if (l.getArea() <= 0){
            srl.setAsError("Área menor ou igual a 0");
            return srl;
        }

        boolean suc = lojaService.updateLoja(l);

        if (suc){
            srl.setAsSuccess("Uma loja foi atualizada.");
        }
        else{
            srl.setAsError("Foi encontrado um erro na atualização da loja "+ l.getId());
        }
        return srl;

    }
	
	@GetMapping("/getAllLoja")
	public List<Loja> getAllLoja(){
		return lojaService.getAllLoja();
	}
	
	@GetMapping("/getLojaById/{id}")
	public Optional<Loja> getLojaById(@PathVariable Long id){
		return lojaService.getLojaById(id);
	}

}
