package edu.icet.demo.dao;


public interface CrudDao <T,String> extends SuperDao{

    boolean save(T dto);
    boolean delete(T dto);
    boolean update(T dto);
   // List<UserEntity> getAll();


}
