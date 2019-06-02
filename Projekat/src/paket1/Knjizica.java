package paket1;
import java.sql.Date;
public class Knjizica {

	
	protected String broj;
	protected String datumIsteka;
	protected String kategorija;
	
	
	public Knjizica(String broj, String datumIsteka, String kategorija,Pacijent pacijent) {
		super();
		this.broj = broj;
		this.datumIsteka = datumIsteka;
		this.kategorija = kategorija;
	}


	public String getBroj() {
		return broj;
	}

	
	public void setBroj(String broj) {
		this.broj = broj;
	}


	public String getDatumIsteka() {
		return datumIsteka;
	}


	public void setDatumIsteka(String datumIsteka) {
		this.datumIsteka = datumIsteka;
	}


	public String getKategorija() {
		return kategorija;
	}


	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}


	@Override
	public String toString() {
		return "Knjizica [broj=" + broj + ", datumIsteka=" + datumIsteka + ", kategorija=" + kategorija + "]";
	}
	
}
