package com.seeandyougo.seeandyougo.repository;

import com.seeandyougo.seeandyougo.table.Connected;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConnectedRepository {
    private final EntityManager em;

    public void save(Connected connected){em.persist(connected);}

    public Connected findRecent(String restaurantName){
        TypedQuery<Connected> query = em.createQuery(
                "SELECT connected FROM Connected connected WHERE connected.name LIKE CONCAT('%', :name, '%') AND connected.time = (SELECT MAX(c2.time) FROM Connected c2 WHERE c2.name LIKE CONCAT('%', :name, '%'))",
                Connected.class
        );
        query.setParameter("name", restaurantName);

        return query.getSingleResult();
    }


    public String findRecentTime(){
        TypedQuery<String> query = em.createQuery(
                "SELECT MAX(ct2.time) FROM Connected ct2",
                String.class
        );
        return query.getSingleResult();
    }

    public Long countNumberOfData(){
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(*) FROM Connected",
                Long.class
        );
        return query.getSingleResult();
    }


}
