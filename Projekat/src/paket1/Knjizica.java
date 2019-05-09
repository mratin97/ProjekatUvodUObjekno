package paket1;
import java.sql.Date;
public class Knjizica {

	
	protected int broj;
	protected Date datumIsteka;
	protected String kategorija;
	
	
	public Knjizica(int broj, Date datumIsteka, String kategorija) {
		super();
		this.broj = broj;
		this.datumIsteka = datumIsteka;
		this.kategorija = kategorija;
	}


	public int getBroj() {
		return broj;
	}


	public void setBroj(int broj) {
		this.broj = broj;
	}


	public Date getDatumIsteka() {
		return datumIsteka;
	}


	public void setDatumIsteka(Date datumIsteka) {
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
