package edu.icet.demo.dao.custom.impl;

import edu.icet.demo.Util.HibernateUtil;
import edu.icet.demo.dao.custom.UserDao;
import edu.icet.demo.entity.UserEntity;
import org.hibernate.Session;


public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public UserEntity search(String id) {
        UserEntity userEntity = null;
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        userEntity = session.get(UserEntity.class, id);
        //user = session.load(User.class,id);
        session.getTransaction().commit();
        session.close();
        return userEntity;
    }

    @Override
    public boolean delete(UserEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.delete(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(UserEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

//    @Override
//    public List<UserEntity> getAll() {
//        List<UserEntity> userEntity= null;
//        Session session = HibernateUtil.getSession();
//        session.getTransaction().begin();
//        userEntity = session.createQuery("from userentity").list();
//        session.getTransaction().commit();
//        session.close();
//        return userEntity;
//    }
}
