package com;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Set;

public class Main {

    static Session session;

    public Main() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        Photo photo1 = new Photo("photo1", today.minusDays(1));
        Photo photo2 = new Photo("photo2", today.minusDays(2));
        Photo photo3 = new Photo("photo3", today.minusDays(3));
        Photo photo4 = new Photo("photo4", today.minusDays(4));
        Photo photo5 = new Photo("photo5", today.minusDays(4));
        Photo photo6 = new Photo("photo6", today.minusDays(4));
        Dao.create(photo1);
        Dao.create(photo2);
        Dao.create(photo3);
        Dao.create(photo4);
        Dao.create(photo5);
        Dao.create(photo6);

//        Album album1 = new Album("album1", "description");
//        Album album2 = new Album("album2", "description", Set.of(photo3, photo4));
//        Album album3 = new Album("album3", "description", Set.of(photo5, photo6));
//        Dao.create(album1);
//        Dao.create(album2);
//        Dao.create(album3);
//
//        User johnDoe = new User("John Doe", Set.of(album1, album2));
////        User janeDoe = new User("Jane Doe");
//        Dao.create(johnDoe);
////        Dao.create(janeDoe);
//
////        Dao.deleteById(User.class, 2);
////
//        johnDoe.likePhoto(photo2);
//
//        Dao.deleteById(Like.class, 1);
//        Like byId = Dao.findById(Like.class, 1);
//        byId.setUser(null);
//        byId.setPhoto(null);
//        Dao.delete(byId);


//        Photo photoById = Dao.findById(Photo.class, 2);
//        User userById = Dao.findById(User.class, 1);
//        Set<Like> userLikes = userById.getLikes();
//        Set<Like> photoLikes = userById.getLikes();

    }

    public static <T> void deleteById(Class<T> clazz, long id) {
        T byId = Dao.findById(clazz, id);
        Transaction transaction = session.beginTransaction();
        session.delete(byId);
        transaction.commit();
    }
}