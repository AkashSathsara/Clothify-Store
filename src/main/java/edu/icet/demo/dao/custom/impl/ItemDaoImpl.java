package edu.icet.demo.dao.custom.impl;

import edu.icet.demo.Util.HibernateUtil;
import edu.icet.demo.dao.custom.ItemDao;
import edu.icet.demo.entity.ItemEntity;
import org.hibernate.Session;


public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public ItemEntity search(String id) {
        ItemEntity itemEntity = null;
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        itemEntity = session.get(ItemEntity.class, id);
        //user = session.load(User.class,id);
        session.getTransaction().commit();
        session.close();
        return itemEntity;
    }

    @Override
    public boolean delete(ItemEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.delete(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ItemEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }


}
