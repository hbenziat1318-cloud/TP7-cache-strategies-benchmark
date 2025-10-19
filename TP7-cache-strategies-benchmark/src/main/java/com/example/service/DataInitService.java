package com.example.service;

import com.example.model.Auteur;
import com.example.model.Categorie;
import com.example.model.Livre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataInitService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-performance");

    public void initData() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Auteur auteur1 = new Auteur("Hemingway", "Ernest", "hemingway@example.com");
        Auteur auteur2 = new Auteur("Orwell", "George", "orwell@example.com");

        Categorie fiction = new Categorie("Fiction");
        Categorie biographie = new Categorie("Biographie");

        Livre livre1 = new Livre("Le Vieil Homme et la Mer", 1952, "978-0141184964");
        Livre livre2 = new Livre("1984", 1949, "978-0451524935");

        auteur1.addLivre(livre1);
        auteur2.addLivre(livre2);

        livre1.addCategorie(fiction);
        livre2.addCategorie(fiction);

        em.persist(fiction);
        em.persist(biographie);
        em.persist(auteur1);
        em.persist(auteur2);
        em.persist(livre1);
        em.persist(livre2);

        em.getTransaction().commit();
        em.close();
    }

    public void close() {
        emf.close();
    }
}
