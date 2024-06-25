package edu.icet.demo.bo.custom;

import edu.icet.demo.bo.SuperBo;
import edu.icet.demo.dto.Supplier;
import edu.icet.demo.entity.SupplierEntity;

public interface SupplierBo extends SuperBo {
    boolean save(Supplier dto);
    SupplierEntity search(String id);
    boolean delete(Supplier dto);
    boolean update(Supplier dto);
}
