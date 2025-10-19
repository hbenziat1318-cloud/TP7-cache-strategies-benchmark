package com.example.service;

import com.example.model.Livre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PerformanceTestService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-performance");

    public void testCachePerformance() {
        EntityManager em = emf.createEntityManager();

        // Premier accès (devrait charger depuis la DB)
        long start = System.currentTimeMillis();
        List<Livre> livres1 = em.createQuery("SELECT l FROM Livre l", Livre.class).getResultList();
        long end = System.currentTimeMillis();
        System.out.println("Temps premier accès: " + (end - start) + " ms");

        // Deuxième accès (devrait charger depuis le cache)
        start = System.currentTimeMillis();
        List<Livre> livres2 = em.createQuery("SELECT l FROM Livre l", Livre.class).getResultList();
        end = System.currentTimeMillis();
        System.out.println("Temps deuxième accès (cache): " + (end - start) + " ms");

        em.close();
    }

    public void close() {
        emf.close();
    }
}
