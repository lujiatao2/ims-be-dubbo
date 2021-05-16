package com.lujiatao.ims.service.repository;

import com.lujiatao.ims.common.entity.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsCategoryDAO {

    boolean insert(GoodsCategory goodsCategory);

    boolean update(GoodsCategory goodsCategory);

    boolean deleteById(int id);

    GoodsCategory selectById(int id);

    List<GoodsCategory> selectByName(String name);

    List<GoodsCategory> selectAll();

}
