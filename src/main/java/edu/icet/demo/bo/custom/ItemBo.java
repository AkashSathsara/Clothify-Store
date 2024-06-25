package edu.icet.demo.bo.custom;

import edu.icet.demo.bo.SuperBo;
import edu.icet.demo.dto.Item;
import edu.icet.demo.entity.ItemEntity;

public interface ItemBo extends SuperBo {
    boolean save(Item dto);
    ItemEntity search(String id);
    boolean delete(Item dto);
    boolean update(Item dto);
}
