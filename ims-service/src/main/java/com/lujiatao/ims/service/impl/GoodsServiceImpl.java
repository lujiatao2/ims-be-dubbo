package com.lujiatao.ims.service.impl;

import com.lujiatao.ims.api.GoodsService;
import com.lujiatao.ims.common.entity.Goods;
import com.lujiatao.ims.service.repository.GoodsDAO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService(version = "1.0.0")
@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsDAO goodsDAO;

    @Autowired
    public GoodsServiceImpl(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    @Override
    public boolean add(Goods goods) {
        return goodsDAO.insert(goods);
    }

    @Override
    public boolean modify(Goods goods) {
        return goodsDAO.update(goods);
    }

    @Override
    public boolean deleteById(int id) {
        return goodsDAO.deleteById(id);
    }

    @Override
    public Goods getById(int id) {
        return goodsDAO.selectById(id);
    }

    @Override
    public List<Goods> search(int goodsCategoryId, String brand, String model) {
        return goodsDAO.select(goodsCategoryId, brand, model);
    }

    @Override
    public List<Goods> getAll() {
        return goodsDAO.selectAll();
    }

}
