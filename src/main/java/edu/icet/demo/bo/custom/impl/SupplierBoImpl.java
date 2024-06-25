package edu.icet.demo.bo.custom.impl;

import edu.icet.demo.Util.DaoType;
import edu.icet.demo.bo.custom.SupplierBo;
import edu.icet.demo.dao.DaoFactory;
import edu.icet.demo.dao.custom.ItemDao;
import edu.icet.demo.dao.custom.SupplierDao;
import edu.icet.demo.dto.Supplier;
import edu.icet.demo.entity.ItemEntity;
import edu.icet.demo.entity.SupplierEntity;
import org.modelmapper.ModelMapper;

public class SupplierBoImpl implements SupplierBo {

    private SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);


    @Override
    public boolean save(Supplier dto) {
        return supplierDao.save(new ModelMapper().map(dto, SupplierEntity.class));

    }

    @Override
    public SupplierEntity search(String id) {
        return supplierDao.search(new ModelMapper().map(id, String.class));
    }

    @Override
    public boolean delete(Supplier dto) {
        return supplierDao.delete(new ModelMapper().map(dto, SupplierEntity.class));
    }

    @Override
    public boolean update(Supplier dto) {
        return supplierDao.update(new ModelMapper().map(dto, SupplierEntity.class));
    }
}
