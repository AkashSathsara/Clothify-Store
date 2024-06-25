package edu.icet.demo.dao.custom.impl;

import edu.icet.demo.Util.HibernateUtil;
import edu.icet.demo.dao.custom.OrderDao;
import edu.icet.demo.entity.OrderDetailEntity;
import edu.icet.demo.entity.OrderEntity;
import org.hibernate.Session;

public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean save(OrderEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public OrderEntity search(String id) {
        OrderEntity orderEntity = null;
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        orderEntity = session.get(OrderEntity.class, id);
        //user = session.load(User.class,id);
        session.getTransaction().commit();
        session.close();
        return orderEntity;
    }


    @Override
    public boolean delete(OrderEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.delete(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OrderEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
