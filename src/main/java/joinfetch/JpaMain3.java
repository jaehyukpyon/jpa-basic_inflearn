package joinfetch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain3 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            PersonC personC = new PersonC();
            personC.setPersonCId(100L);
            personC.setPersonCName("personC");

            PersonD personD = new PersonD();
            personD.setPersonDId(1000L);
            personD.setPersonDName("personD");

            PersonB personB = new PersonB();
            personB.setPersonBId(10L);
            personB.setPersonBName("personB");
            personB.setPersonC(personC);
            personB.setPersonD(personD);

            em.persist(personC);
            em.persist(personD);
            em.persist(personB);

            PersonA personA = new PersonA();
            personA.setPersonAId(1L);
            personA.setPersonAName("personA");
            personA.setPersonB(personB);

            em.persist(personA);

            em.flush();
            em.clear();

            PersonA findPersonA = em.find(PersonA.class, 1L);
            System.out.println(findPersonA.getPersonB().getClass().getName());
            System.out.println(findPersonA.getPersonB().getPersonBName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

    /*
    *
    * Hibernate:
    select
        persona0_.personAId as personAI1_10_0_,
        persona0_.personAName as personAN2_10_0_,
        persona0_.personb_id as personb_3_10_0_
    from
        PersonA persona0_
    where
        persona0_.personAId=?

    joinfetch.PersonB$HibernateProxy$YkWa5nED

    Hibernate:
        select
            personb0_.personBId as personBI1_11_0_,
            personb0_.personBName as personBN2_11_0_,
            personb0_.personc_id as personc_3_11_0_,
            personb0_.persond_id as persond_4_11_0_,
            personc1_.personCId as personCI1_12_1_,
            personc1_.personCName as personCN2_12_1_,
            persond2_.personDId as personDI1_13_2_,
            persond2_.personDName as personDN2_13_2_
        from
            PersonB personb0_
        left outer join
            PersonC personc1_
                on personb0_.personc_id=personc1_.personCId
        left outer join
            PersonD persond2_
                on personb0_.persond_id=persond2_.personDId
        where
            personb0_.personBId=?
*/

}

