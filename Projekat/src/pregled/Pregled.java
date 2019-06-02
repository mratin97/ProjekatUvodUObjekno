package pregled;
import java.sql.Date;
import paket1.Pacijent;
import paket1.Lekar;
public class Pregled {
	
	protected Pacijent pacijent;
	protected String opis;
	protected String Status;
	protected String IdPregleda;
	protected String Termin;
	protected Lekar Doktor;
	
	
	
	public Pregled(Pacijent pacijent, String opis, String status, String idPregleda, String termin, Lekar doktor) {
		super();
		this.pacijent = pacijent;
		this.opis = opis;
		Status = status;
		IdPregleda = idPregleda;
		Termin = termin;
		Doktor = doktor;
	}



	public Pacijent getPacijent() {
		return pacijent;
	}



	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}



	public String getOpis() {
		return opis;
	}



	public void setOpis(String opis) {
		this.opis = opis;
	}



	public String getStatus() {
		return Status;
	}



	public void setStatus(String status) {
		Status = status;
	}



	public String getIdPregleda() {
		return IdPregleda;
	}



	public void setIdPregleda(String idPregleda) {
		IdPregleda = idPregleda;
	}



	public String getTermin() {
		return Termin;
	}



	public void setTermin(String termin) {
		Termin = termin;
	}



	public Lekar getDoktor() {
		return Doktor;
	}



	public void setDoktor(Lekar doktor) {
		Doktor = doktor;
	}



	@Override
	public String toString() {
		return "Pregled [pacijent=" + pacijent + ", opis=" + opis + ", Status=" + Status + ", IdPregleda=" + IdPregleda
				+ ", Termin=" + Termin + ", Doktor=" + Doktor + "]";
	}
	
	
	
}
