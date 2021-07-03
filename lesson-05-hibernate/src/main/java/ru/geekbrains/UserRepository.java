package ru.geekbrains;

import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserRepository {

    private final EntityManagerFactory emFactory;

    public UserRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<User> findAll() {
        return executeForEntityManager(
                em -> em.createQuery("select u from User u", User.class).getResultList()
        );
    }

    public Optional<User> findById(long id) {
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(User.class, id))
        );
    }

    public void insert(User user) {
        executeInTransaction(
                em -> em.persist(user)
        );
    }

    public void update(User user) {
        executeInTransaction(
                em -> em.merge(user)
        );
    }

    public void delete(long id) {
        executeInTransaction(
                em -> em.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate()
        );
    }

    public void save(User user) {
        if (user.getId() == null) {
            insert(user);
        } else {
            update(user);
        }
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            em.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
