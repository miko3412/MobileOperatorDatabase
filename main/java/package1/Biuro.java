package package1;


public class Biuro {

	private int nr_biura;
	private String nazwa;
	private String data_zalozenia;
	private int nr_adresu;
	
	public Biuro () {
		
	}

	public Biuro(int nr_biura, String nazwa, String data_zalozenia, int nr_adresu) {
		super();
		this.nr_biura = nr_biura;
		this.nazwa = nazwa;
		this.data_zalozenia = data_zalozenia;
		this.nr_adresu = nr_adresu;
	}

	public int getNr_biura() {
		return nr_biura;
	}

	public void setNr_biura(int nr_biura) {
		this.nr_biura = nr_biura;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getData_zalozenia() {
		return data_zalozenia;
	}

	public void setData_zalozenia(String data_zalozenia) {
		this.data_zalozenia = data_zalozenia;
	}

	public int getNr_adresu() {
		return nr_adresu;
	}

	public void setNr_adresu(int nr_adresu) {
		this.nr_adresu = nr_adresu;
	}

	@Override
	public String toString() {
		return "Biuro [nr_biura=" + nr_biura + ", nazwa=" + nazwa + ", data_zalozenia=" + data_zalozenia
				+ ", nr_adresu=" + nr_adresu + "]";
	}
	
	
}
