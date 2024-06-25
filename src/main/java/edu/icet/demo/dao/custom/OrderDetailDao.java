package edu.icet.demo.dao.custom;

import edu.icet.demo.dao.CrudDao;
import edu.icet.demo.entity.OrderDetailEntity;
import edu.icet.demo.entity.OrderEntity;

public interface OrderDetailDao extends CrudDao<OrderDetailEntity,String> {
    OrderDetailEntity search(String id);

}
