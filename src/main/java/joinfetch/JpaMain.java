package joinfetch;

import cascade.Child;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            
            TeamJoin team = new TeamJoin();
            team.setTeamId(1L);
            team.setTeamName("teamA");
            
            em.persist(team);

            ChildJoin childJoin1 = new ChildJoin();
            childJoin1.setChildId(100L);
            childJoin1.setChildName("childA");

            ChildJoin childJoin2 = new ChildJoin();
            childJoin2.setChildId(200L);
            childJoin2.setChildName("childB");

            ChildJoin childJoin3 = new ChildJoin();
            childJoin3.setChildId(300L);
            childJoin3.setChildName("childC");

            em.persist(childJoin1);
            em.persist(childJoin2);
            em.persist(childJoin1);
            
            MemberJoin member1 = new MemberJoin();
            member1.setMemberId(10L);
            member1.setMemberName("memberA");
            member1.setTeam(team);
            member1.setChildJoin(childJoin1);

            MemberJoin member2 = new MemberJoin();
            member2.setMemberId(20L);
            member2.setMemberName("memberB");
            member2.setTeam(team);
            member2.setChildJoin(childJoin2);

            MemberJoin member3 = new MemberJoin();
            member3.setMemberId(30L);
            member3.setMemberName("memberC");
            member3.setTeam(team);
            member3.setChildJoin(childJoin3);
            
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            
            em.flush();
            em.clear();
            
            String jpql = "SELECT t FROM TeamJoin t join fetch t.members";
            List<TeamJoin> teams = em.createQuery(jpql, TeamJoin.class).getResultList();
            System.out.println(teams.size()); // 3
            System.out.println(teams.get(0).getMembers().get(0).getTeam().getClass().getName()); // Proxy 객체가 아님
            System.out.println(teams.get(0).getMembers().get(0).getTeam().getTeamName()); // teamA (select 쿼리 발생 X)
            System.out.println(teams.get(0) == teams.get(0).getMembers().get(0).getTeam()); // true

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
