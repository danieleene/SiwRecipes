package it.uniroma3.siw.model;

import java.util.Date;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Ricetta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String titolo;
	private String descrizione;
	private String procedimento;
	private Integer tempo;
	private String difficolta;
	private String categoria;
	private Date data;
	
	//Metodi Getter e Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}
	public Integer getTempo() {
		return tempo;
	}
	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}
	public String getDifficolta() {
		return difficolta;
	}
	public void setDifficolta(String difficolta) {
		this.difficolta = difficolta;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	//Metodi equals e hashCode
	
	@Override
	public int hashCode() {
		return Objects.hash(categoria, data, descrizione, difficolta, id, procedimento, tempo, titolo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ricetta other = (Ricetta) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(data, other.data)
				&& Objects.equals(descrizione, other.descrizione) && Objects.equals(difficolta, other.difficolta)
				&& Objects.equals(id, other.id) && Objects.equals(procedimento, other.procedimento)
				&& Objects.equals(tempo, other.tempo) && Objects.equals(titolo, other.titolo);
	}
	
	
}
