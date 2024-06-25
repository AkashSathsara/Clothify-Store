package edu.icet.demo.bo.custom.impl;

import edu.icet.demo.Util.DaoType;
import edu.icet.demo.bo.custom.UserBo;
import edu.icet.demo.dao.DaoFactory;
import edu.icet.demo.dao.custom.UserDao;
import edu.icet.demo.dto.User;
import edu.icet.demo.entity.UserEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserBoImpl implements UserBo {

    private UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);

    @Override
    public boolean save(User dto) {
        return userDao.save(new ModelMapper().map(dto, UserEntity.class));
    }

    @Override
    public UserEntity search(String id) {
        return userDao.search(new ModelMapper().map(id, String.class));
    }

    @Override
    public boolean delete(User dto) {
        return userDao.delete(new ModelMapper().map(dto, UserEntity.class));

    }

    @Override
    public boolean update(User dto) {
        return userDao.update(new ModelMapper().map(dto, UserEntity.class));
    }

//    @Override
//    public List<UserEntity> getAll() {
//        return userDao.getAll();
//    }


}
