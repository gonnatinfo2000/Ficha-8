package ficha8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ficha8.dto.SimpleResponse;
import ficha8.dto.SimpleResponseAndar;
import ficha8.model.Andar;
import ficha8.service.AndarService;

@RestController
public class AndarController {

	private final AndarService andarService;

	@Autowired
	public AndarController(AndarService andarService) {
		this.andarService = andarService;
	}
	
	@PostMapping("/addAndar")
	public ResponseEntity<SimpleResponse> addAndar(@RequestBody Andar andar){
		SimpleResponseAndar sra = new SimpleResponseAndar();

		if(andar.getNumero_andar() < 0) {
			sra.setMessage("Número de andar inválido. Número menor do que 0.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sra);
		}
		
		if(andar.getNumero_max_lojas() < 1) {
			sra.setMessage("Numero máximo de andares do centro comercial inválido. Número menor do que 1.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sra);
		}
		
		if(andarService.addAndar(andar)) {
			sra.setAsSuccess("Um novo andar foi inserido.");
			// srcc.setCentroComercial(centroComercialService.getAllCentroComercial());
		}else{
			sra.setAsError("Foi encontrado um erro ao inserir um andar.");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(sra);
	}
	
	@PostMapping("/addAndar/{andar_id}/centroComercial/{centro_comercial_id}")
	public String addAndarToCentroComercial(@PathVariable String andar_id, @PathVariable String centro_comercial_id) {
		return andarService.addAndarToCentroComercial(andar_id, centro_comercial_id);
	}
	
	@DeleteMapping("/deleteAndar/{id}")
	public SimpleResponse deleteAndar(@PathVariable String id) {
		SimpleResponseAndar sra = new SimpleResponseAndar();
		
		if(andarService.deleteAndar(id)) {
			sra.setAsSuccess("Um andar foi apagado.");
			sra.setAndares(andarService.getAllAndar());
		}else{
			sra.setAsError("Foi encontrado um erro ao apagar um andar.");
		}
		
		return sra;
	}
	
	@GetMapping("/getAllAndar")
	public List<Andar> gettAllAndar(){
		return andarService.getAllAndar();
	}
	
}
