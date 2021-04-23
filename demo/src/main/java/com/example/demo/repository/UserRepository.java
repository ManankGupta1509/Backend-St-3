package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class UserRepository {

    @PersistenceUnit(name = "dataProject")
    private EntityManagerFactory entityManagerFactory;

    public void registerUser(User newUser)
    {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction=entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(newUser);
            transaction.commit();
        }
        catch (Exception e){
            System.out.println(e);
            transaction.rollback();
        }
    }

    public User checkCredentials(String username, String password) {

        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        }
        catch(NoResultException e){
            System.out.println(e);
            return null;
        }
    }
}
