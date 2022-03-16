package ficha8.dto;

import java.util.ArrayList;
import java.util.List;

import ficha8.model.Andar;

public class SimpleResponseAndar extends SimpleResponse {
    
	List<Andar> andares;

	public SimpleResponseAndar() {
		this.andares = new ArrayList<>();
	}

	public List<Andar> getAndares() {
		return andares;
	}

	public void setAndares(List<Andar> andares) {
		this.andares = andares;
	}
	
}
