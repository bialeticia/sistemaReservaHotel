package sistemaReservaHotel;

public class Hospede extends Thread {
	private String nome;
	private Quarto quarto;
	private Hotel hotel;
	
	public Hospede(String nome, Hotel hotel) {
		this.nome = nome;
		this.hotel = hotel;
	}
	
	public void run() {
		try {
            while (!interrupted()) {
            	if (quarto != null) {
            		if (Math.random() < 0.5) {
                		if (quarto.getSituacaoHospede() == SituacaoHospede.quarto) {
                			quarto.sairDoQuarto(); 
                		} else {
                			quarto.tentarVoltarAoQuarto();
                		}
                    } else {
                    	hotel.realizarCheckout(this);
                    	quarto.removerHospede(this);
//                    	quarto = null;
                    }
            	}
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Hospede interrompido: " + e.getMessage());
        }
    }
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
