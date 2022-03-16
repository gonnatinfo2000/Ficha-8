package ficha8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ficha8.model.Andar;
import ficha8.model.Centro_Comercial;
import ficha8.repository.andarRepository;
import ficha8.repository.centroComercialRepository;

import static java.lang.Float.NaN;
import static java.lang.Long.parseLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AndarService {
	
	private final andarRepository andarRepo;
	private final centroComercialRepository ccRepo;
	
	@Autowired
	public AndarService(andarRepository andarRepo, centroComercialRepository ccRepo) {
		this.andarRepo = andarRepo;
		this.ccRepo = ccRepo;
	}
	
	public boolean addAndar(Andar andar) {
		if(andar.getId() == null) {
			andarRepo.save(andar);
			return true;
		}
		return false;
	}
	
	public String addAndarToCentroComercial(String andar_id, String centro_comercial_id) {
		Optional<Andar> opcionalAndar = andarRepo.findById(Long.parseLong(andar_id));
		Optional<Centro_Comercial> opcionalCC = ccRepo.findById(Long.parseLong(centro_comercial_id));
		
		if(opcionalAndar.isPresent() && opcionalCC.isPresent()) {
			Andar andarAux = opcionalAndar.get();
			Centro_Comercial ccAux = opcionalCC.get();
			
			ccAux.addAndar(ccAux, andarAux);
			andarAux.setCentro_comercial(ccAux);
			
			ccRepo.save(ccAux);
			andarRepo.save(andarAux);
			
			return "Um andar foi acrescentado ao centro comercial.";
		}
		return "Foi encontrado um erro ao acrescentar o andar ao centro comercial.";
	}
	
	public boolean deleteAndar(String id) {
		try {
			Long idLong = parseLong(id);
			
			if(id == null || idLong==NaN || andarRepo.findById(idLong).isEmpty()) {
				return false;
			}
			
			Andar andar = andarRepo.findById(idLong).get();
			andarRepo.delete(andar);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public List<Andar> getAllAndar(){
		List<Andar> listaAndar = new ArrayList<>();
		
		andarRepo.findAll().forEach(listaAndar::add);
		
		return listaAndar;
	}
	

}
