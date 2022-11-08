package both_direction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main3 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team t1 = new Team();
            t1.setId(10L);
            t1.setTeamName("name1");

            System.out.println("===== before1 =====");
            em.persist(t1);
            System.out.println("===== after1 =====");

            Team t2 = new Team();
            t2.setId(10L);
            t2.setTeamName("name2");


            System.out.println("===== before2 =====");
            em.persist(t2); // em.persist(t2)를 실행할 경우, before2까지만 실행 되고, 이 코드 아래부터 실행이 아예 안됨.
            //em.merge(t2);
            System.out.println("===== after2 =====");

            System.out.println("t1 persisted? " + (em.contains(t1))); // true (merge일 경우)
            System.out.println("t2 persisted? " + (em.contains(t2))); // false (merge일 경우)

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}
