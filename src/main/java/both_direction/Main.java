package both_direction;

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
            Team t1 = new Team();
            t1.setId(1L);
            t1.setTeamName("first Team");
            em.persist(t1);

            em.flush();
            em.clear();

            Member member = new Member();
            member.setId(20L);
            member.setName("first member");
            member.setTeam(t1);

            System.out.println("===== before =====");
            em.persist(member); // persist 호출 히, Team1 테이블에 select query가 나감.
            System.out.println("===== after =====");

            tx.commit();

            // 연관관계 설정 시, 주인이 영속성 컨텍스트에 저장될 때, 연관된 엔티티는 무조건 영속성 컨텍스트 안에 있는 상태여야 한다.
            // Team t1을 저장한 후 em.clear()로 초기화 하고, Member 엔티티를 영속성 컨텍스트에 저장하는 그 순간 (persist 메서드를 호출하는 그 순간에)
            // 자기와 연관된 Team t1 엔티티가 영속성 컨텍스트에 없으면 t1 을 찾기 위해 select query를 db에 날린다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}
