package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {

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

            List<Team> resultList = em.createQuery("SELECT m.team FROM Member m", Team.class)
                    .getResultList();
            /*
            * select
            team1_.id as id1_3_,
            team1_.name as name2_3_
                from
                Member member0_
                    inner join
                Team team1_
                    on member0_.TEAM_ID=team1_.id

            * inner join 해서 Team이랑 join하고 있는데, 실제 SQL 입장에서는 Member랑 Team을 join해서 연관된 Team을 찾아야 하기 때문.
            * */

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
