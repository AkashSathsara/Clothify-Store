package edu.icet.demo.bo.custom;

import edu.icet.demo.bo.SuperBo;
import edu.icet.demo.dto.Order;
import edu.icet.demo.entity.OrderEntity;

public interface OrderBo extends SuperBo {
    boolean save(Order dto);
    OrderEntity search(String id);
    boolean delete(Order dto);
    boolean update(Order dto);
}
