package org.example.DAOs;

import jakarta.transaction.Transactional;
import org.example.Models.Candidatos;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.Models.Candidatos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CandidatoDAO {

    @PersistenceContext(unitName = "org.examplePersistenceUnit")
    private EntityManager em;

    @Transactional
    public List<Candidatos> getAllUsuarios() {
        return em.createQuery("SELECT u FROM Candidatos u", Candidatos.class)
                .getResultList();
    }

    @Transactional
    public Candidatos getCandidatobyLista(String lista) {
        return em.find(Candidatos.class, lista);
    }
}