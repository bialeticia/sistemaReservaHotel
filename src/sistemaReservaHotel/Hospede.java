package sistemaReservaHotel;

public class Hospede extends Thread {
	private String nome;
	private Quarto quarto;
	
	public Hospede(String nome) {
		this.nome = nome;
	}
	
	public void run() {
		try {
            while (!interrupted()) {
            	if (Math.random() < 0.5) {
                    quarto.sairDoQuarto();
                } else {
                	quarto.tentarVoltarAoQuarto();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Hospede interrompido: " + e.getMessage());
        }
    }
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
}
