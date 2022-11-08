package both_direction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main2 {

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

            System.out.println("===== before1 =====");
            em.persist(member);
            System.out.println("===== after1 =====");

            System.out.println("===== before2 =====");
            member.setTeam(t1);
            System.out.println("===== after2 =====");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    /*

    insert
    into
        Team1
        (teamName, id)
    values
        (?, ?)
===== before1 =====
===== after1 =====
===== before2 =====
===== after2 =====
Hibernate:
    select
        team_.id,
        team_.teamName as teamName2_5_
    from
        Team1 team_
    where
        team_.id=?
Hibernate:
    insert
    into
        Member1
        (name, team_id, id)
    values
        (?, ?, ?)
Hibernate:
    update
        Member1
    set
        name=?,
        team_id=?
    where
        id=?
    * */

}
