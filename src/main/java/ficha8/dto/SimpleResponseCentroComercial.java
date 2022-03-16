package ficha8.dto;

import java.util.ArrayList;
import java.util.List;

import ficha8.model.Centro_Comercial;

public class SimpleResponseCentroComercial extends SimpleResponse {
    
	List<Centro_Comercial> centros;

	public SimpleResponseCentroComercial() {
		this.centros = new ArrayList<>();
	}

	public List<Centro_Comercial> getCentros() {
		return centros;
	}

	public void setCentros(List<Centro_Comercial> centros) {
		this.centros = centros;
	}
	
}
