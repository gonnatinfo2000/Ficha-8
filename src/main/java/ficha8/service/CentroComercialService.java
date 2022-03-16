package ficha8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ficha8.model.Centro_Comercial;
import ficha8.repository.centroComercialRepository;

import static java.lang.Float.NaN;
import static java.lang.Long.parseLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CentroComercialService {

	private final centroComercialRepository ccRepo;
	
	@Autowired
	public CentroComercialService(centroComercialRepository ccRepo) {
		this.ccRepo = ccRepo;
	}
	
	public boolean addCentroComercial(Centro_Comercial cc) {
		if(cc.getId() == null) {
			ccRepo.save(cc);
			return true;
		}
		return false;
	}
	
	public boolean deleteCentroComercial(String id) {
		try {
			Long idLong = parseLong(id);
			
			if(id == null || idLong==NaN || ccRepo.findById(idLong).isEmpty()) {
				return false;
			}
			
			Centro_Comercial cc = ccRepo.findById(idLong).get();
			ccRepo.delete(cc);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public List<Centro_Comercial> getAllCentroComercial(){
		List<Centro_Comercial> listaCC = new ArrayList<>();
		
		ccRepo.findAll().forEach(listaCC::add);
		
		return listaCC;
	}
	
	public Optional<Centro_Comercial> getCentroComercialById(Long id) {
		return ccRepo.findById(id);
	}
	
}
