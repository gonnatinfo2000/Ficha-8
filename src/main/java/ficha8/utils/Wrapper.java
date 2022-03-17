package ficha8.utils;

import ficha8.model.Andar;
import ficha8.model.Centro_Comercial;
import ficha8.model.Loja;

public class Wrapper {
	
	private Centro_Comercial cc;
	private Andar andar;
	private Loja loja;
	
	public Wrapper(Centro_Comercial cc, Andar andar, Loja loja) {
		this.cc = cc;
		this.andar = andar;
		this.loja = loja;
	}

	public Centro_Comercial getCc() {
		return cc;
	}

	public void setCc(Centro_Comercial cc) {
		this.cc = cc;
	}

	public Andar getAndar() {
		return andar;
	}

	public void setAndar(Andar andar) {
		this.andar = andar;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

}
