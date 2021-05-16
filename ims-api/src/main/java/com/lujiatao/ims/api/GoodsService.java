package com.lujiatao.ims.api;

import com.lujiatao.ims.common.entity.Goods;

import java.util.List;

public interface GoodsService {

    boolean add(Goods goods);

    boolean modify(Goods goods);

    boolean deleteById(int id);

    Goods getById(int id);

    List<Goods> search(int goodsCategoryId, String brand, String model);

    List<Goods> getAll();

}
