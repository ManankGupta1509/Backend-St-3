package com.example.demo.repository;

import com.example.demo.model.dataList;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;

@Repository
public class dataListRepository {
    public dataListRepository(){}


    @PersistenceUnit(unitName = "dataProject")
    private EntityManagerFactory entityManagerFactory;
    public ArrayList<dataList> getData(Integer userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<dataList> query=entityManager.createQuery("SELECT d from dataList d JOIN FETCH d.user duser WHERE duser.id = :userId",dataList.class);
        query.setParameter("userId",userId);
        ArrayList<dataList> result = (ArrayList<dataList>)query.getResultList();
        return result;
    }

    public void createPost(dataList newPost) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction= entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(newPost);
            transaction.commit();
        }
        catch(Exception e){
            System.out.println(e);
            transaction.rollback();
        }
    }
    public void deletePost(Integer postId)
    {EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction= entityManager.getTransaction();
        try{
            transaction.begin();
            dataList post=entityManager.find(dataList.class,postId);
            entityManager.remove(post);
            transaction.commit();
        }
        catch(Exception e){
            System.out.println(e);
            transaction.rollback();
        }
    }

}
