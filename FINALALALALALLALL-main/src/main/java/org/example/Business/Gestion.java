package org.example.Business;
import org.example.DAOs.CandidatoDAO;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.Models.Candidatos;
import org.example.Models.Candidatos;

import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class Gestion {

    @Inject
    private CandidatoDAO candidatoDao;

    public List<Candidatos> getCandidatos() {
        return candidatoDao.getAllUsuarios();
    }

    public Candidatos getCandidatoLista(String lista) {
        return candidatoDao.getCandidatobyLista(lista);
    }



}