package com.example.demo.repository;

import com.example.demo.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoRepository extends JpaRepository<Corso,Integer> {

    @Query("Select c from Corso c where c.durata =:d")
    List<Corso> findCorsoByDurata(@Param("d") String durata);
}
