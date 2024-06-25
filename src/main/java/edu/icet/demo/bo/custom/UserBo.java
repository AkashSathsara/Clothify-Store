package edu.icet.demo.bo.custom;

import edu.icet.demo.bo.SuperBo;
import edu.icet.demo.dto.User;
import edu.icet.demo.entity.UserEntity;


public interface UserBo extends SuperBo {
    boolean save(User dto);
    UserEntity search(String id);
    boolean delete(User dto);
    boolean update(User dto);
   // List<UserEntity> getAll();
}
