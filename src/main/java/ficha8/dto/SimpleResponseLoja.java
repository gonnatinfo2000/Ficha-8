package ficha8.dto;

import java.util.ArrayList;
import java.util.List;

import ficha8.model.Loja;

public class SimpleResponseLoja extends SimpleResponse {
    
	List<Loja> lojas;

	public SimpleResponseLoja() {
		this.lojas = new ArrayList<>();
	}

	public List<Loja> getLoja() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}
	
}

