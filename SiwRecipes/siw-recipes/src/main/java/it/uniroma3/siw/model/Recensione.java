package it.uniroma3.siw.model;

import java.util.Date;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Recensione {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String testo;
	private Integer voto;
	private Date data;
	
	//Metodi Getter e Setter
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public Integer getVoto() {
		return voto;
	}
	public void setVoto(Integer voto) {
		this.voto = voto;
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
		return Objects.hash(data, id, testo, voto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recensione other = (Recensione) obj;
		return Objects.equals(data, other.data) && Objects.equals(id, other.id) && Objects.equals(testo, other.testo)
				&& Objects.equals(voto, other.voto);
	}
	
}
