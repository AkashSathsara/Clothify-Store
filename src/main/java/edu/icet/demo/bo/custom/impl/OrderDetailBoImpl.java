package edu.icet.demo.bo.custom.impl;

import edu.icet.demo.Util.DaoType;
import edu.icet.demo.bo.custom.OrderDetailBo;
import edu.icet.demo.dao.DaoFactory;
import edu.icet.demo.dao.custom.OrderDetailDao;
import edu.icet.demo.dto.OrderDetail;
import edu.icet.demo.entity.OrderDetailEntity;
import org.modelmapper.ModelMapper;

public class OrderDetailBoImpl implements OrderDetailBo {
    private OrderDetailDao orderDetailDao = DaoFactory.getInstance().getDao(DaoType.ORDERDETAIL);

    @Override
    public boolean save(OrderDetail dto) {
        return orderDetailDao.save(new ModelMapper().map(dto, OrderDetailEntity.class));
    }

    @Override
    public OrderDetailEntity search(String id) {
        return orderDetailDao.search(new ModelMapper().map(id, String.class));
    }

    @Override
    public boolean delete(OrderDetail dto) {
        return orderDetailDao.delete(new ModelMapper().map(dto, OrderDetailEntity.class));
    }

    @Override
    public boolean update(OrderDetail dto) {
        return orderDetailDao.update(new ModelMapper().map(dto, OrderDetailEntity.class));
    }
}
