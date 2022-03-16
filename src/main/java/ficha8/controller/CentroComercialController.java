package ficha8.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ficha8.dto.SimpleResponse;
import ficha8.dto.SimpleResponseCentroComercial;
import ficha8.model.Centro_Comercial;
import ficha8.service.CentroComercialService;

@RestController
public class CentroComercialController {
	
	private final CentroComercialService centroComercialService;

	@Autowired
	public CentroComercialController(CentroComercialService centroComercialService) {
		this.centroComercialService = centroComercialService;
	}
	
	@PostMapping("/addCentroComercial")
	public ResponseEntity<SimpleResponse> addCentroComercial(@RequestBody Centro_Comercial cc){
		SimpleResponseCentroComercial srcc = new SimpleResponseCentroComercial();
		
		if(cc.getNome() == null || cc.getNome().isBlank()) {
			srcc.setMessage("Nome do centro comercial inválido.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srcc);
		}
		
		if(cc.getMorada() == null || cc.getMorada().isBlank()) {
			srcc.setMessage("Morada do centro comercial inválido.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srcc);
		}
		
		if(cc.getNumero_max_andar() < 0) {
			srcc.setMessage("Numero máximo de andares do centro comercial inválido. Número menor do que 0.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srcc);
		}
		
		if(centroComercialService.addCentroComercial(cc)) {
			srcc.setAsSuccess("Um novo centro comercial inserido.");
			// srcc.setCentroComercial(centroComercialService.getAllCentroComercial());
		}else{
			srcc.setAsError("Foi encontrado um erro ao inserir um centro comercial.");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(srcc);
		
	}
	
	@DeleteMapping("/deleteCentroComercial/{id}")
	public SimpleResponse deleteCentroComercial(@PathVariable String id) {
		SimpleResponseCentroComercial srcc = new SimpleResponseCentroComercial();
		
		if(centroComercialService.deleteCentroComercial(id)) {
			srcc.setAsSuccess("Um centro comercial foi apagado.");
			srcc.setCentros(centroComercialService.getAllCentroComercial());
		}else{
			srcc.setAsError("Foi encontrado um erro ao apagar um centro comercial.");
		}
		
		return srcc;
	}
	
	@GetMapping("/getAllCentroComercial")
	public List<Centro_Comercial> gettAllCentroComercial(){
		return centroComercialService.getAllCentroComercial();
	}
	
	@GetMapping("/getCentroComercialById/{id}")
	public Optional<Centro_Comercial> getCentroComercialById(@PathVariable Long id){
		return centroComercialService.getCentroComercialById(id);
	}

}
