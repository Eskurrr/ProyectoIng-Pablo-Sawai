package data;

import java.util.List;

public class Medidas {
	protected List <Integer> medida ;
	protected String fecha;
	public Medidas(List<Integer> medida, String fecha) {
		this.medida = medida;
		this.fecha = fecha;
	}

	public List<Integer> getMedida() {
		return medida;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setMedida(List<Integer> medida) {
		this.medida = medida;
	}

}
