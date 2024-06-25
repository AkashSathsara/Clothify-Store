package edu.icet.demo.bo.custom.impl;

import edu.icet.demo.Util.DaoType;
import edu.icet.demo.bo.custom.OrderBo;
import edu.icet.demo.dao.DaoFactory;
import edu.icet.demo.dao.custom.OrderDao;
import edu.icet.demo.dto.Order;
import edu.icet.demo.entity.OrderEntity;
import org.modelmapper.ModelMapper;

public class OrderBoImpl implements OrderBo {
    private OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);

    @Override
    public boolean save(Order dto) {
        return orderDao.save(new ModelMapper().map(dto, OrderEntity.class));
    }

    @Override
    public OrderEntity search(String id) {
        return orderDao.search(new ModelMapper().map(id, String.class));
    }

    @Override
    public boolean delete(Order dto) {
        return orderDao.delete(new ModelMapper().map(dto, OrderEntity.class));
    }

    @Override
    public boolean update(Order dto) {
        return orderDao.update(new ModelMapper().map(dto, OrderEntity.class));
    }
}
