package edu.icet.demo.dao;

import edu.icet.demo.Util.DaoType;
import edu.icet.demo.dao.custom.impl.*;

public class DaoFactory{
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance!=null?instance:(instance=new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case USER:return (T)new UserDaoImpl();
            case ITEM:return (T)new ItemDaoImpl();
            case SUPPLIER:return (T)new SupplierDaoImpl();
            case ORDER:return (T)new OrderDaoImpl();
            case ORDERDETAIL:return (T)new OrderDetailDaoImpl();

        }
        return null;
    }

}
