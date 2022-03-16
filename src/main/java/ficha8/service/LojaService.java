package ficha8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ficha8.model.Andar;
import ficha8.model.Loja;
import ficha8.repository.andarRepository;
import ficha8.repository.lojaRepository;

import static java.lang.Float.NaN;
import static java.lang.Long.parseLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LojaService {
	
	private final lojaRepository lojaRepo;
	private final andarRepository andarRepo;
	
	@Autowired
	public LojaService(lojaRepository lojaRepo, andarRepository andarRepo) {
		this.lojaRepo = lojaRepo;
		this.andarRepo = andarRepo;
	}
	
	public boolean addLoja(Loja loja) {
		if(loja.getId() == null) {
			lojaRepo.save(loja);
			return true;
		}
		return false;
	}
	
	public String addLojaToAndar(String loja_id, String andar_id) {
		Optional<Loja> opcionalLoja = lojaRepo.findById(Long.parseLong(loja_id));
		Optional<Andar> opcionalAndar = andarRepo.findById(Long.parseLong(andar_id));
		
		if(opcionalLoja.isPresent() && opcionalAndar.isPresent()) {
			Loja lojaAux = opcionalLoja.get();
			Andar andarAux = opcionalAndar.get();
			
			andarAux.addLoja(andarAux, lojaAux);
			lojaAux.setAndar(andarAux);
			
			lojaRepo.save(lojaAux);
			andarRepo.save(andarAux);
			
			return "Uma loja foi acrescentada ao andar.";
		}
		
		return "Foi encontrado um erro ao acrescentar uma loja ao andar.";
	}
	
	public boolean deleteLoja(String id) {
		try {
			Long idLong = parseLong(id);
			
			if(id == null || idLong==NaN || lojaRepo.findById(idLong).isEmpty()) {
				return false;
			}
			
			Loja loja = lojaRepo.findById(idLong).get();
			lojaRepo.delete(loja);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
    public boolean updateLoja(Loja loja){
        if (loja.getId() == null || lojaRepo.findById(loja.getId()).isEmpty()){
            return false;
        }

        Loja l = lojaRepo.findById(loja.getId()).get();

        if (l.getNome() != null || l.getNome().isBlank()){
            l.setNome(loja.getNome());
        }

        if (l.getNumero_funcionarios()>0){
            l.setNumero_funcionarios(loja.getNumero_funcionarios());
        }
        
        if (l.getArea()>0){
            l.setArea(loja.getArea());
        }

        lojaRepo.save(l);

        return true;
    }

	public List<Loja> getAllLoja(){
		List<Loja> listaLoja = new ArrayList<>();
		
		lojaRepo.findAll().forEach(listaLoja::add);
		
		return listaLoja;
	}
	
	public Optional<Loja> getLojaById(Long id) {
		return lojaRepo.findById(id);
	}
	
}
