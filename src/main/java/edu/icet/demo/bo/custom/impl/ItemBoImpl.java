package edu.icet.demo.bo.custom.impl;

import edu.icet.demo.Util.DaoType;
import edu.icet.demo.bo.custom.ItemBo;
import edu.icet.demo.dao.DaoFactory;
import edu.icet.demo.dao.custom.ItemDao;
import edu.icet.demo.dao.custom.UserDao;
import edu.icet.demo.dto.Item;
import edu.icet.demo.entity.ItemEntity;
import edu.icet.demo.entity.UserEntity;
import org.modelmapper.ModelMapper;

public class ItemBoImpl implements ItemBo {

    private ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);

    @Override
    public boolean save(Item dto) {
        return itemDao.save(new ModelMapper().map(dto, ItemEntity.class));
    }

    @Override
    public ItemEntity search(String id) {
        return itemDao.search(new ModelMapper().map(id, String.class));
    }

    @Override
    public boolean delete(Item dto) {
        return itemDao.delete(new ModelMapper().map(dto, ItemEntity.class));
    }

    @Override
    public boolean update(Item dto) {
        return itemDao.update(new ModelMapper().map(dto, ItemEntity.class));
    }
}
