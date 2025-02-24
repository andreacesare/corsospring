package com.example.demo.repository;

import com.example.demo.entity.Docente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DocenteCustomRepositoryImpl implements DocenteCustomRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Docente> ricerca(String text){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Docente> cq = cb.createQuery(Docente.class);
        Root<Docente> root = cq.from(Docente.class);

        List<Predicate> predicates = new ArrayList<>();

        if(text!=null && !text.trim().isEmpty()){
            String like="%"+text.toLowerCase()+"%";
            Predicate nome=cb.like(cb.lower(root.get("nome")),like);
            Predicate cognome=cb.like(cb.lower(root.get("cognome")),like);
            predicates.add(cb.or(nome,cognome));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Docente> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
