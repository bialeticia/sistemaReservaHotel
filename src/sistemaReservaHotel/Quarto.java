package sistemaReservaHotel;

public class Quarto {
	private int numero;
	private EstadoQuarto estado = EstadoQuarto.limpo;
	private int capacidade = 4;
	
	public Quarto(int numero, EstadoQuarto estado) {
		this.numero = numero;
		this.estado = estado;
	}

	public EstadoQuarto getEstado() {
		return estado;
	}

	public void setEstado(EstadoQuarto estado) {
		this.estado = estado;
	} 
	
}
