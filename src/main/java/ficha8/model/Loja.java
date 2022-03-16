package ficha8.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Loja")
public class Loja {
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
	
	@ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "andar_id", nullable = false)
    private Andar andar;
	
	private String nome;
	private int numero_funcionarios;
	private int area;
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumero_funcionarios() {
		return numero_funcionarios;
	}
	public void setNumero_funcionarios(int numero_funcionarios) {
		this.numero_funcionarios = numero_funcionarios;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}

	public Andar getAndar() {
		return andar;
	}

	public void setAndar(Andar andar) {
		this.andar = andar;
	}

	@Override
	public String toString() {
		return "Loja [id=" + id + ", nome=" + nome + ", numero_funcionarios=" + numero_funcionarios + ", area=" + area
				+ "]";
	}

}
