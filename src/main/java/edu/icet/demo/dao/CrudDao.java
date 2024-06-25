package edu.icet.demo.dao;

import edu.icet.demo.dto.User;
import edu.icet.demo.entity.ItemEntity;
import edu.icet.demo.entity.UserEntity;
import javafx.collections.ObservableList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface CrudDao <T,String> extends SuperDao{

    boolean save(T dto);
    boolean delete(T dto);
    boolean update(T dto);
   // List<UserEntity> getAll();


}
