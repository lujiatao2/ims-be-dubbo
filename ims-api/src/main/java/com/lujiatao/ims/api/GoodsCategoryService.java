package com.lujiatao.ims.api;

import com.lujiatao.ims.common.entity.GoodsCategory;

import java.util.List;

public interface GoodsCategoryService {

    boolean add(GoodsCategory goodsCategory);

    boolean modify(GoodsCategory goodsCategory);

    boolean deleteById(int id);

    GoodsCategory getById(int id);

    List<GoodsCategory> search(String name);

    List<GoodsCategory> getAll();

}
