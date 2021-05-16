package com.lujiatao.ims.service.repository;

import com.lujiatao.ims.common.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsDAO {

    boolean insert(Goods goods);

    boolean update(Goods goods);

    boolean deleteById(int id);

    Goods selectById(int id);

    List<Goods> select(int goodsCategoryId, String brand, String model);

    List<Goods> selectAll();

}
