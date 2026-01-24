package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;
	private double quantita;
	private String unita;

	@ManyToOne
	@JoinColumn(name = "ricetta_id")
	private Ricetta ricetta;
	
	//Metodi Getter e Setter 
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getQuantita() {
		return quantita;
	}
	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}
	public String getUnita() {
		return unita;
	}
	public void setUnita(String unita) {
		this.unita = unita;
	}

	public Ricetta getRicetta() {
		return ricetta;
	}
	public void setRicetta(Ricetta ricetta) {
		this.ricetta = ricetta;
	}
	
	//Metodi equals e hashCode
	
	@Override
	public int hashCode() {
		return Objects.hash(nome, quantita, unita);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		return Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(quantita) == Double.doubleToLongBits(other.quantita)
				&& Objects.equals(unita, other.unita);
	}
		
}


