import jakarta.persistence.*;

@Entity
@Table(name = "geisternetz")
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double breitengrad;
    private Double laengengrad;
    private String beschreibung;
    
    private String meldendeName;
    private String meldendeTelefon; // Optional

    @Column(name = "bergendeName")  // FIX: JPA nutzt jetzt den richtigen DB-Spaltennamen
    private String bergendePerson;

    private String bergendeTelefon;
    private String bergendeEmail;

    private String verschollenName;
    private String verschollenTelefon;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Double groesse; // Größe des Netzes
    private String bergendeBemerkung; 

    // Enum zur Verwaltung der verschiedenen Status
    public enum Status {
        GEMELDET, 
        BERGUNG_BEVORSTEHEND, 
        GEBORGEN, 
        VERSCHOLLEN
    }

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getBreitengrad() { return breitengrad; }
    public void setBreitengrad(Double breitengrad) { this.breitengrad = breitengrad; }

    public Double getLaengengrad() { return laengengrad; }
    public void setLaengengrad(Double laengengrad) { this.laengengrad = laengengrad; }

    public String getBeschreibung() { return beschreibung; }
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }

    public String getMeldendeName() { return meldendeName; }
    public void setMeldendeName(String meldendeName) { this.meldendeName = meldendeName; }

    public String getMeldendeTelefon() { return meldendeTelefon; }
    public void setMeldendeTelefon(String meldendeTelefon) { this.meldendeTelefon = meldendeTelefon; }

    public String getBergendePerson() { return bergendePerson; }
    public void setBergendePerson(String bergendePerson) { this.bergendePerson = bergendePerson; }

    public String getBergendeTelefon() { return bergendeTelefon; }
    public void setBergendeTelefon(String bergendeTelefon) { this.bergendeTelefon = bergendeTelefon; }

    public String getBergendeEmail() { return bergendeEmail; }
    public void setBergendeEmail(String bergendeEmail) { this.bergendeEmail = bergendeEmail; }

    public String getVerschollenName() { return verschollenName; }
    public void setVerschollenName(String verschollenName) { this.verschollenName = verschollenName; }

    public String getVerschollenTelefon() { return verschollenTelefon; }
    public void setVerschollenTelefon(String verschollenTelefon) { this.verschollenTelefon = verschollenTelefon; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Double getGroesse() { return groesse; }
    public void setGroesse(Double groesse) { this.groesse = groesse; }

    public String getBergendeBemerkung() { return bergendeBemerkung; }
    public void setBergendeBemerkung(String bergendeBemerkung) { this.bergendeBemerkung = bergendeBemerkung; }
}
