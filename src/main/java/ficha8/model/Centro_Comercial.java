package ficha8.model;

import java.util.List;

import javax.persistence.*;

import ficha8.dto.SimpleResponse;

@Entity
@Table(name = "Centro_Comercial")
public class Centro_Comercial {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "centro_comercial")
	private List<Andar> andares;
	
	private String nome;
	private String morada;
	private int numero_max_andar;
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMorada() {
		return morada;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	public int getNumero_max_andar() {
		return numero_max_andar;
	}
	public void setNumero_max_andar(int numero_max_andar) {
		this.numero_max_andar = numero_max_andar;
	}

	public List<Andar> getAndares() {
		return andares;
	}

	public void setAndares(List<Andar> andares) {
		this.andares = andares;
	}

	@Override
	public String toString() {
		return "Centro_Comercial [id=" + id + ", nome=" + nome + ", morada=" + morada + ", numero_max_andar="
				+ numero_max_andar + "]";
	}
	
    public void addAndar(Centro_Comercial cc, Andar andar){
    	SimpleResponse sr = new SimpleResponse();
        if(andares.size() == cc.getNumero_max_andar()) {
        	sr.setAsError("Não é possível acrescentar mais andares. O número máximo de andares para este centro comercial foi antigido.");
        }else{
            andares.add(andar);
        }
    }
	
}
