package edu.icet.demo.bo;

import edu.icet.demo.Util.BoType;
import edu.icet.demo.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory instance;
    private BoFactory(){}

    public static BoFactory getInstance(){
        return instance!=null?instance:(instance=new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case USER:return (T) new UserBoImpl();
            case ITEM:return (T) new ItemBoImpl();
            case SUPPLIER:return (T)new SupplierBoImpl();
            case ORDER:return (T)new OrderBoImpl();
            case ORDERDETAIL:return (T)new OrderDetailBoImpl();
        }
        return null;
    }
}
