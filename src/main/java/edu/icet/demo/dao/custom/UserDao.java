package edu.icet.demo.dao.custom;

import edu.icet.demo.dao.CrudDao;
import edu.icet.demo.dto.User;
import edu.icet.demo.entity.UserEntity;

public interface UserDao extends CrudDao<UserEntity,String> {
    UserEntity search(String id);
}
