package com.example.demo.repository;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class CorsoCustomRepositoryImpl implements CorsoCustomRepository {
    @PersistenceContext
    EntityManager entityManger;

    @Override
    public List<Corso> ricerca(String nome, String data, String durata, String docente){
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder cb = entityManger.getCriteriaBuilder();
        CriteriaQuery<Corso> cq = cb.createQuery(Corso.class);
        Root<Corso> root = cq.from(Corso.class);
        Join<Corso,Docente> docenteJoin = root.join("docente");

        if(nome!=null && !nome.trim().isEmpty()){
            predicates.add(cb.like(cb.lower(root.get("nome")), "%"+nome.toLowerCase()+"%"));
        }

        if(data!=null && !data.trim().isEmpty()){
            predicates.add(cb.like(root.get("dataInizio"), "%"+data+"%"));
        }

        if(durata!=null && !durata.trim().isEmpty()){
            predicates.add(cb.equal(root.get("durata"), durata));
        }

        if(docente!=null && !docente.isEmpty()){
            String likeDoc="%"+docente.toLowerCase()+"%";
            Predicate nomePredicate=cb.like(cb.lower(docenteJoin.get("nome")),likeDoc);
            Predicate cognomePredicate=cb.like(cb.lower(docenteJoin.get("cognome")),likeDoc);
            predicates.add(cb.or(nomePredicate,cognomePredicate));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Corso> query=entityManger.createQuery(cq);
        return query.getResultList();
    }
}
