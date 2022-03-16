package ficha8.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ficha8.dto.SimpleResponse;

@Entity
@Table(name = "Andar")
public class Andar {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
	
	private int numero_andar;
	private int numero_max_lojas;
	
	@ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "centro_comercial", nullable = false)
    private Centro_Comercial centro_comercial;
    
    @OneToMany(mappedBy = "andar")
	private List<Loja> lojas;
	
	public Long getId() {
		return id;
	}
	
	public int getNumero_andar() {
		return numero_andar;
	}

	public void setNumero_andar(int numero_andar) {
		this.numero_andar = numero_andar;
	}

	public int getNumero_max_lojas() {
		return numero_max_lojas;
	}

	public void setNumero_max_lojas(int numero_max_lojas) {
		this.numero_max_lojas = numero_max_lojas;
	}

	public Centro_Comercial getCentro_comercial() {
		return centro_comercial;
	}

	public void setCentro_comercial(Centro_Comercial centro_comercial) {
		this.centro_comercial = centro_comercial;
	}

	@Override
	public String toString() {
		return "Andar [id=" + id + ", numero_andar=" + numero_andar + ", numero_max_lojas=" + numero_max_lojas + "]";
	}

    public void addLoja(Andar andar, Loja loja){
    	SimpleResponse sr = new SimpleResponse();
        if(lojas.size() == andar.getNumero_max_lojas()) {
        	sr.setAsError("Não é possível acrescentar mais lojas. O número máximo de lojas para este andar foi antigido.");
        }else{
            lojas.add(loja);
        }
    }

}
