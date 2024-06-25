package edu.icet.demo.dao.custom.impl;

import edu.icet.demo.Util.HibernateUtil;
import edu.icet.demo.dao.custom.OrderDetailDao;
import edu.icet.demo.entity.OrderDetailEntity;
import org.hibernate.Session;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public OrderDetailEntity search(String id) {
        OrderDetailEntity orderDetailEntity = null;
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        orderDetailEntity = session.get(OrderDetailEntity.class, id);
        //user = session.load(User.class,id);
        session.getTransaction().commit();
        session.close();
        return orderDetailEntity;
    }

    @Override
    public boolean save(OrderDetailEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(OrderDetailEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.delete(dto);
        session.getTransaction().commit();
        session.close();
        return true;        }

    @Override
    public boolean update(OrderDetailEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(dto);
        session.getTransaction().commit();
        session.close();
        return true;     }
}
