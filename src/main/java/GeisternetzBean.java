import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class GeisternetzBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghost");
    private EntityManager entityManager = emf.createEntityManager();

    private Geisternetz geisternetz = new Geisternetz();

    public List<Geisternetz> getAllGeisternetze() {
        return entityManager.createQuery("SELECT g FROM Geisternetz g", Geisternetz.class).getResultList();
    }

    public List<Geisternetz> getOffeneGeisternetze() {
        return entityManager.createQuery("SELECT g FROM Geisternetz g WHERE g.status IN (:gemeldet, :bergung)", Geisternetz.class)
                .setParameter("gemeldet", Geisternetz.Status.GEMELDET)
                .setParameter("bergung", Geisternetz.Status.BERGUNG_BEVORSTEHEND)
                .getResultList();
    }

    public void speichern() {
        entityManager.getTransaction().begin();
        geisternetz.setStatus(Geisternetz.Status.GEMELDET);
        entityManager.persist(geisternetz);
        entityManager.getTransaction().commit();
        resetAllFields();
    }

    public void eintragenFuerBergung(Long id) {
        entityManager.getTransaction().begin();
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        if (netz != null && netz.getStatus() == Geisternetz.Status.GEMELDET) {
            netz.setBergendePerson(geisternetz.getBergendePerson());
            netz.setBergendeTelefon(geisternetz.getBergendeTelefon());
            netz.setStatus(Geisternetz.Status.BERGUNG_BEVORSTEHEND);
            entityManager.merge(netz);
        }
        entityManager.getTransaction().commit();
        resetBergungFields();
    }

    public void alsVerschollenMelden(Long id) {
        entityManager.getTransaction().begin();
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        if (netz != null) {
            netz.setVerschollenName(geisternetz.getVerschollenName());
            netz.setVerschollenTelefon(geisternetz.getVerschollenTelefon());
            netz.setStatus(Geisternetz.Status.VERSCHOLLEN);
            entityManager.merge(netz);
        }
        entityManager.getTransaction().commit();
        resetVerschollenFields();
    }

    public void bergen(Long id) {
        entityManager.getTransaction().begin();
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        if (netz != null && netz.getStatus() == Geisternetz.Status.BERGUNG_BEVORSTEHEND) {
            netz.setStatus(Geisternetz.Status.GEBORGEN);
            entityManager.merge(netz);
        }
        entityManager.getTransaction().commit();
    }

    // Eingabefelder für Bergung zurücksetzen
    private void resetBergungFields() {
        geisternetz.setBergendePerson("");
        geisternetz.setBergendeTelefon("");
    }

    // Eingabefelder für Verschollen zurücksetzen
    private void resetVerschollenFields() {
        geisternetz.setVerschollenName("");
        geisternetz.setVerschollenTelefon("");
    }

    // Alle Eingabefelder zurücksetzen (z. B. nach Speichern)
    private void resetAllFields() {
        geisternetz = new Geisternetz();
    }

    public Geisternetz getGeisternetz() {
        return geisternetz;
    }

    public void setGeisternetz(Geisternetz geisternetz) {
        this.geisternetz = geisternetz;
    }
}
