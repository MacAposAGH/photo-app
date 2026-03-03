package com;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Dao {
    public static final Session SESSION = HibernateUtil.getSessionFactory().openSession();

    public static <T> void create(T object) {
        Transaction transaction = SESSION.beginTransaction();
        SESSION.save(object);
        transaction.commit();
    }

    public static <T> T findById(Class<T> clazz, long id) {
        String hql = String.format("from %s where id = :id", clazz.getSimpleName());
        Query<T> query = SESSION.createQuery(hql, clazz);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public static User findUserByPhotoId(long id) {
        String hql = """
                select u
                from User u
                          join u.albums a
                          join a.photos p
                where p.id = :id
                """;
        Query<User> query = SESSION.createQuery(hql, User.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public static Like findLikesByUserAndPhoto(long userId, long photoId) {
        String hql = """
                select l from Like l where l.user.id = :id1 and l.photo.id = :id2
                """;
        Query<Like> query = SESSION.createQuery(hql, Like.class);
        query.setParameter("id1", userId);
        query.setParameter("id2", photoId);
        return query.uniqueResult();
    }

    public static List<User> findLikesUsersByPhotoId(long id) {
        String hql = """
                select l.user from Like l where l.photo.id = :id
                """;
        Query<User> query = SESSION.createQuery(hql, User.class);
        query.setParameter("id", id);
        return query.list();
    }

    public static Album findPhotosByAlbumId(long id) {
        String hql = """
                select a from Album a where a.id=:id
                """;
        Query<Album> query = SESSION.createQuery(hql, Album.class);
        query.setParameter("id", id);
        Album album = query.uniqueResult();
        return album;
    }

    public static <T> void delete(T entity) {
        Transaction transaction = SESSION.beginTransaction();
        SESSION.delete(entity);
        transaction.commit();
    }

    public static <T> void deleteById(Class<T> clazz, long id) {
        delete(findById(clazz, id));
    }
}