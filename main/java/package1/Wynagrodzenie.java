package package1;


public class Wynagrodzenie {
	
	private int nr_wynagrodzenia;
	private String data;
	private int kwota_pdst;
	private int kwota_dod;
	private int nr_pracownika;
	
	public Wynagrodzenie() {
		
	}
	
	public Wynagrodzenie(int nr_wynagrodzenia, String data, int kwota_pdst, int kwota_dod, int nr_pracownika) {
		super();
		this.nr_wynagrodzenia = nr_wynagrodzenia;
		this.data = data;
		this.kwota_pdst = kwota_pdst;
		this.kwota_dod = kwota_dod;
		this.nr_pracownika = nr_pracownika;
	}

	public int getNr_wynagrodzenia() {
		return nr_wynagrodzenia;
	}

	public void setNr_wynagrodzenia(int nr_wynagrodzenia) {
		this.nr_wynagrodzenia = nr_wynagrodzenia;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getKwota_pdst() {
		return kwota_pdst;
	}

	public void setKwota_pdst(int kwota_pdst) {
		this.kwota_pdst = kwota_pdst;
	}

	public int getKwota_dod() {
		return kwota_dod;
	}

	public void setKwota_dod(int kwota_dod) {
		this.kwota_dod = kwota_dod;
	}

	public int getNr_pracownika() {
		return nr_pracownika;
	}

	public void setNr_pracownika(int nr_pracownika) {
		this.nr_pracownika = nr_pracownika;
	}
	
	
}
