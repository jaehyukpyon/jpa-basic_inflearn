package proxy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("name1");

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===== 1 =====");
            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("===== 1 =====");

            System.out.println("===== 2 =====");
            System.out.println("findMember.getId() = " + findMember.getId()); // 이때 실제 DB 쿼리 실행 X
            System.out.println("===== 2 =====");

            System.out.println("===== 3 =====");
            System.out.println("findMember.getName() = " + findMember.getName()); // 이때 실제 DB select query 실행.
            System.out.println("===== 3 =====");

            System.out.println(em.find(Member.class, member.getId()).getClass().getName()); // proxy

            System.out.println("is in persistence context? " + em.contains(findMember)); // true

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}
