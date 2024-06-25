package edu.icet.demo.bo.custom;

import edu.icet.demo.bo.SuperBo;
import edu.icet.demo.dto.OrderDetail;
import edu.icet.demo.dto.Supplier;
import edu.icet.demo.entity.OrderDetailEntity;
import edu.icet.demo.entity.OrderEntity;

public interface OrderDetailBo extends SuperBo {
    boolean save(OrderDetail dto);
    OrderDetailEntity search(String id);
    boolean delete(OrderDetail dto);
    boolean update(OrderDetail dto);
}
