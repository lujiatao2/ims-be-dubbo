package com.lujiatao.ims.service.impl;

import com.lujiatao.ims.api.GoodsCategoryService;
import com.lujiatao.ims.common.entity.GoodsCategory;
import com.lujiatao.ims.service.repository.GoodsCategoryDAO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService(version = "1.0.0")
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private final GoodsCategoryDAO goodsCategoryDAO;

    @Autowired
    public GoodsCategoryServiceImpl(GoodsCategoryDAO goodsCategoryDAO) {
        this.goodsCategoryDAO = goodsCategoryDAO;
    }

    @Override
    public boolean add(GoodsCategory goodsCategory) {
        return goodsCategoryDAO.insert(goodsCategory);
    }

    @Override
    public boolean modify(GoodsCategory goodsCategory) {
        return goodsCategoryDAO.update(goodsCategory);
    }

    @Override
    public boolean deleteById(int id) {
        return goodsCategoryDAO.deleteById(id);
    }

    @Override
    public GoodsCategory getById(int id) {
        return goodsCategoryDAO.selectById(id);
    }

    @Override
    public List<GoodsCategory> search(String name) {
        return goodsCategoryDAO.selectByName(name);
    }

    @Override
    public List<GoodsCategory> getAll() {
        return goodsCategoryDAO.selectAll();
    }

}
