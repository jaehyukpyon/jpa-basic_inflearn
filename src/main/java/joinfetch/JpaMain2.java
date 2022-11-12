package joinfetch;

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
            
            TeamJoin team = new TeamJoin();
            team.setTeamId(1L);
            team.setTeamName("teamA");
            
            em.persist(team);

            Ancestor ancestor1 = new Ancestor();
            ancestor1.setAncestorId(1000L);
            ancestor1.setAncestorName("ancestor1");

            Ancestor ancestor2 = new Ancestor();
            ancestor2.setAncestorId(2000L);
            ancestor2.setAncestorName("ancestor2");

            Ancestor ancestor3 = new Ancestor();
            ancestor3.setAncestorId(3000L);
            ancestor3.setAncestorName("ancestor3");

            em.persist(ancestor1);
            em.persist(ancestor2);
            em.persist(ancestor3);

            Final finalEntity1 = new Final();
            finalEntity1.setFinalId(10000L);
            finalEntity1.setFinalName("finalA");

            Final finalEntity2 = new Final();
            finalEntity2.setFinalId(20000L);
            finalEntity2.setFinalName("finalB");

            Final finalEntity3 = new Final();
            finalEntity3.setFinalId(30000L);
            finalEntity3.setFinalName("finalC");

            em.persist(finalEntity1);
            em.persist(finalEntity2);
            em.persist(finalEntity3);

            ChildJoin childJoin1 = new ChildJoin();
            childJoin1.setChildId(100L);
            childJoin1.setChildName("childA");
            childJoin1.setAncestor(ancestor1);
            childJoin1.setFinalEntity(finalEntity1);

            ChildJoin childJoin2 = new ChildJoin();
            childJoin2.setChildId(200L);
            childJoin2.setChildName("childB");
            childJoin2.setAncestor(ancestor2);
            childJoin2.setFinalEntity(finalEntity2);

            ChildJoin childJoin3 = new ChildJoin();
            childJoin3.setChildId(300L);
            childJoin3.setChildName("childC");
            childJoin3.setAncestor(ancestor3);
            childJoin3.setFinalEntity(finalEntity3);

            em.persist(childJoin1);
            em.persist(childJoin2);
            em.persist(childJoin3);
            
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
            
            String jpql = "SELECT t FROM TeamJoin t join fetch t.members m join fetch m.childJoin";
            List<TeamJoin> teams = em.createQuery(jpql, TeamJoin.class).getResultList();
            System.out.println(teams.size()); // 3
            System.out.println(teams.get(0).getMembers().get(0).getTeam().getClass().getName()); // Proxy 객체가 아님
            System.out.println(teams.get(0).getMembers().get(0).getTeam().getTeamName()); // teamA (select 쿼리 발생 X)
            System.out.println(teams.get(0) == teams.get(0).getMembers().get(0).getTeam()); // true

            for (TeamJoin teamJoin : teams) {
                for (MemberJoin memberJoin : teamJoin.getMembers()) {
                    System.out.println(memberJoin.getMemberId() + ", " + memberJoin.getMemberName() + ", " + memberJoin.getChildJoin().getChildName());
                }
            }

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
