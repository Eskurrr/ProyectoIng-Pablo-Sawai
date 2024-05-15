package data;

import java.util.ArrayList;
import java.util.List;

public class UsuarioConM extends Usuario{
	protected List <Medidas> med ;

	public UsuarioConM(String nombre , List<Medidas> med) {
		super(nombre);
		this.med = med;
	}
	public UsuarioConM(String nombre) {
		super(nombre);
		this.med = new ArrayList<>();
	}
	public List<Medidas> getMed() {
		return med;
	}
	public void setMed(List<Medidas> med) {
		this.med = med;
	}


}
