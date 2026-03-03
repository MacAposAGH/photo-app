package com;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Dao {
    private static final Session SESSION = HibernateUtil.getSessionFactory().openSession();
    ;

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

    public static <T> void deleteById(Class<T> clazz, long id) {
        T byId = findById(clazz, id);
        Transaction transaction = SESSION.beginTransaction();
        SESSION.delete(byId);
        transaction.commit();
    }
}