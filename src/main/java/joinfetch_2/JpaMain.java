package joinfetch_2;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            EntityB entityB = new EntityB();
            entityB.setEntityBId(1L);
            entityB.setEntityBName("entityB");

            em.persist(entityB);

            EntityA entityA = new EntityA();
            entityA.setEntityAId(10L);
            entityA.setEntityAName("entityA");
            entityA.setEntityB(entityB);

            em.persist(entityA);

            em.flush();
            em.clear();

            String query = "SELECT a FROM EntityA a";

            List<EntityA> resultList = em.createQuery(query, EntityA.class).getResultList();

            EntityB findB = resultList.get(0).getEntityB();
            System.out.println(findB.getClass().getName()); // proxy

            String query2 = "SELECT b FROM EntityB b";
            List<EntityB> resultList1 = em.createQuery(query2, EntityB.class).getResultList();
            System.out.println(resultList1.get(0).getClass().getName()); // proxy
            System.out.println(findB == resultList1.get(0)); // true

            System.out.println("==============");
            resultList1.get(0).getEntityBName(); // select query가 발생되지 않는다.
            System.out.println("==============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

}
