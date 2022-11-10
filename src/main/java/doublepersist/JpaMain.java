package doublepersist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setMemberId(1L);
            member.setMemberName("helloJPA");
            em.persist(member);

            Member member2 = new Member();
            member2.setMemberId(1L);
            member2.setMemberName("helloJPA");

            em.persist(member2);

            /*
            * caused by: A different object with the same identifier value was already associated with the session : [doublepersist.Member#1]
javax.persistence.EntityExistsException: A different object with the same identifier value was already associated with the session : [doublepersist.Member#1]
	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:123)
	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:181)
	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:188)
	at org.hibernate.internal.SessionImpl.firePersist(SessionImpl.java:807)
	at org.hibernate.internal.SessionImpl.persist(SessionImpl.java:785)
	at doublepersist.JpaMain.main(JpaMain.java:27)*/
            tx.commit();
        } catch (Exception e) {
            System.out.println("caused by: " + e.getMessage());
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}
