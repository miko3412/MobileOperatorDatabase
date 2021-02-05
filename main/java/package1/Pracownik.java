package package1;


public class Pracownik {

	private int nr_pracownika;
	private String imie;
	private String nazwisko;
	private String data_urodzenia;
	private String pesel;
	private String plec;
	private String nr_telefonu;
	private int nr_biura;
	private int nr_adresu;
	
	public Pracownik() {
		
	}

	



	





	public Pracownik(int nr_pracownika, String imie, String nazwisko, String data_urodzenia, String pesel,
			String nr_telefonu, String plec, int nr_biura, int nr_adresu) {
		super();
		this.nr_pracownika = nr_pracownika;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.data_urodzenia = data_urodzenia;
		this.pesel = pesel;
		this.nr_telefonu = nr_telefonu;
		this.plec = plec;
		this.nr_biura = nr_biura;
		this.nr_adresu = nr_adresu;
	}











	public String getNr_telefonu() {
		return nr_telefonu;
	}

	public void setNr_telefonu(String nr_telefonu) {
		this.nr_telefonu = nr_telefonu;
	}

	public String getPlec() {
		return plec;
	}

	public void setPlec(String plec) {
		this.plec = plec;
	}

	public int getNr_pracownika() {
		return nr_pracownika;
	}

	public void setNr_pracownika(int nr_pracownika) {
		this.nr_pracownika = nr_pracownika;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getData_urodzenia() {
		return data_urodzenia;
	}

	public void setData_urodzenia(String data_urodzenia) {
		this.data_urodzenia = data_urodzenia;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public int getNr_biura() {
		return nr_biura;
	}

	public void setNr_biura(int nr_biura) {
		this.nr_biura = nr_biura;
	}

	public int getNr_adresu() {
		return nr_adresu;
	}

	public void setNr_adresu(int nr_adresu) {
		this.nr_adresu = nr_adresu;
	}

	@Override
	public String toString() {
		return "Pracownik [nr_pracownika=" + nr_pracownika + ", imie=" + imie + ", nazwisko=" + nazwisko
				+ ", data_urodzenia=" + data_urodzenia + ", pesel=" + pesel + ", plec=" + plec + ", nr_telefonu="
				+ nr_telefonu + ", nr_biura=" + nr_biura + ", nr_adresu=" + nr_adresu + "]";
	}
	
	
	
}
