package edu.icet.demo.dao.custom.impl;

import edu.icet.demo.Util.HibernateUtil;
import edu.icet.demo.dao.custom.SupplierDao;
import edu.icet.demo.entity.SupplierEntity;
import org.hibernate.Session;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public SupplierEntity search(String id) {
        SupplierEntity supplierEntity = null;
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        supplierEntity = session.get(SupplierEntity.class, id);
        //user = session.load(User.class,id);
        session.getTransaction().commit();
        session.close();
        return supplierEntity;    }

    @Override
    public boolean save(SupplierEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(dto);
        session.getTransaction().commit();
        session.close();

        return true;    }

    @Override
    public boolean delete(SupplierEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.delete(dto);
        session.getTransaction().commit();
        session.close();
        return true;    }

    @Override
    public boolean update(SupplierEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
