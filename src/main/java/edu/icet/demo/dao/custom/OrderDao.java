package edu.icet.demo.dao.custom;

import edu.icet.demo.dao.CrudDao;
import edu.icet.demo.dao.SuperDao;
import edu.icet.demo.entity.OrderEntity;

public interface OrderDao extends CrudDao<OrderEntity,String> {
    OrderEntity search(String id);
}
