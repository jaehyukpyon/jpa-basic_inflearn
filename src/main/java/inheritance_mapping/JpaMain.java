package inheritance_mapping;

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
            Movie movie = new Movie();
            movie.setDirector("영화디렉터1");
            movie.setActor("영화배우1");
            movie.setName("영화제목1");
            movie.setPrice(10000);

            Movie movie2 = new Movie();
            movie2.setDirector("영화디렉터2");
            movie2.setActor("영화배우2");
            movie2.setName("영화제목2");
            movie2.setPrice(20000);

            Album album = new Album();
            album.setName("앨범1");
            album.setArtist("앨범아티스트1");
            album.setPrice(45000);

            Album album2 = new Album();
            album2.setName("앨범2");
            album2.setArtist("앨범아티스트2");
            album2.setPrice(25000);

            Book book = new Book();
            book.setIsbn("책isbn1");
            book.setName("책제목1");
            book.setPrice(30000);

            Book book2 = new Book();
            book2.setIsbn("책isbn2");
            book2.setName("책제목2");
            book2.setPrice(40000);

            em.persist(movie);
            em.persist(movie2);

            em.persist(album);
            em.persist(album2);

            em.persist(book);
            em.persist(book2);

            em.flush();
            em.clear();

            Item item = em.find(Item.class, 1L);
            System.out.println("item.getName() = " + item.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    /*
    * em.find(Movie.class, 1L);
      select
        movie0_.id as id1_2_0_,
        movie0_1_.name as name2_2_0_,
        movie0_1_.price as price3_2_0_,
        movie0_.actor as actor1_3_0_,
        movie0_.director as director2_3_0_
    from
        Movie movie0_
    inner join
        Item movie0_1_
            on movie0_.id=movie0_1_.id
    where
        movie0_.id=?*/
}
