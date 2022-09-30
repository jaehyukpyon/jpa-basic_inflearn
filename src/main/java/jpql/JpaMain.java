package jpql;

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
            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member result = em.createQuery("SELECT m FROM Member m WHERE m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult(); // Member와 연관된 Team 엔티티를 join하여 select query를 하지 않고, 오직 순수하게 Member 엔티티만 select 조회 query를 날린다...
            // 단, em.createQuery() 하기 전에, em.flush(), em.clear()를 먼저 하고 em.createQuery()를 실행하면, 먼저 Member 엔티티 조회 후, 그 다음 해당 Member 엔티티와 연관된 Team 엔티티를 별도의 select query로 조회한다.
            // (즉, Member 조회를 위한 select query 한 번, 그 이후 해당 Member 엔티티와 연관된 Team 엔티티를 조회하기 위한 별도의 select query 한 번 >> 총 2번의 select query)...

            System.out.println("result.getUsername() = " + result.getUsername());

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
