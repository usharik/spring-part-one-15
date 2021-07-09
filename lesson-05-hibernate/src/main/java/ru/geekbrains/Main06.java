package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Contact;
import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class Main06 {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        // INSERT for one to many
//        em.getTransaction().begin();
//
//        User user = new User(null, "user_with_contacts_2", 25);
//        user.addContact(new Contact(null, "phone", "1245333", user));
//        user.addContact(new Contact(null, "email", "user21@mail.com", user));
//        user.addContact(new Contact(null, "address", "Land1, City2, Street", user));
//
//        em.persist(user);
//
//        em.getTransaction().commit();

        // SELECT

        //User user = em.find(User.class, 11L);
//        User user = em.createQuery("select u from User u join fetch u.contacts where u.id = :id", User.class)
//                .setParameter("id", 11L)
//                .getSingleResult();
//        System.out.println(user);
//        user.getContacts().forEach(System.out::println);

        // N + 1
        //em.createQuery("select c from Contact c join fetch c.user", Contact.class).getResultList();

        em.createQuery("select u " +
                "from User u " +
                "inner join Contact c " +
                "where u.username like '%a%' and c.type = 'Phone'", User.class);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);

        Join<Object, Object> contacts = root.join("contacts", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(root.get("username"), "%a%"));
        predicates.add(cb.equal(contacts.get("type"), "Phone"));

        List<User> resultList = em.createQuery(query
                .select(root)
                .where(predicates.toArray(new Predicate[0]))).getResultList();

        em.close();
    }
}
