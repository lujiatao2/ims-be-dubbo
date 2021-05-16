package com.lujiatao.ims.web.controller;

import com.lujiatao.ims.api.GoodsCategoryService;
import com.lujiatao.ims.api.GoodsService;
import com.lujiatao.ims.common.entity.Goods;
import com.lujiatao.ims.common.entity.GoodsCategory;
import com.lujiatao.ims.common.model.BaseVO;
import com.lujiatao.ims.web.util.Paginationer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods-category")
@ResponseBody
public class GoodsCategoryController {

    @DubboReference(version = "1.0.0")
    private GoodsCategoryService goodsCategoryService;
    @DubboReference(version = "1.0.0")
    private GoodsService goodsService;
    private final Paginationer paginationer = new Paginationer();

    @GetMapping
    public BaseVO goodsCategories() {
        return new BaseVO(goodsCategoryService.getAll());
    }

    @GetMapping("/all")
    public BaseVO all() {
        List<GoodsCategory> list = goodsCategoryService.getAll();
        Object[] objects = paginationer.getTargetDatas(list, 1);
        return new BaseVO(objects);
    }

    @GetMapping("/page")
    public BaseVO page(@RequestParam("name") String name, @RequestParam("targetPage") int targetPage) {
        List<GoodsCategory> list;
        if (name.equals("")) {
            list = goodsCategoryService.getAll();
        } else {
            list = goodsCategoryService.search(name);
        }
        Object[] objects = paginationer.getTargetDatas(list, targetPage);
        return new BaseVO(objects);
    }

    @GetMapping("/search")
    public BaseVO search(@RequestParam("name") String name) {
        List<GoodsCategory> list;
        if (name.equals("")) {
            list = goodsCategoryService.getAll();
        } else {
            list = goodsCategoryService.search(name);
        }
        Object[] objects = paginationer.getTargetDatas(list, 1);
        return new BaseVO(objects);
    }

    @PostMapping
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO add(@RequestBody GoodsCategory goodsCategory) {
        int id = goodsCategory.getId();
        GoodsCategory tmp = goodsCategoryService.getById(id);
        if (tmp == null) {
            return goodsCategoryService.add(goodsCategory) ? new BaseVO() : new BaseVO(1, "未知异常！");
        } else {
            return new BaseVO(1, "该记录已存在！");
        }
    }

    @PutMapping
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO modify(@RequestBody GoodsCategory goodsCategory) {
        int id = goodsCategory.getId();
        GoodsCategory tmp = goodsCategoryService.getById(id);
        if (tmp != null) {
            return goodsCategoryService.modify(goodsCategory) ? new BaseVO() : new BaseVO(1, "未知异常！");
        } else {
            return new BaseVO(1, "该记录不存在！");
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO delete(@RequestParam("id") int id) {
        GoodsCategory tmp = goodsCategoryService.getById(id);
        if (tmp == null) {
            return new BaseVO(1, "该记录不存在！");
        }
        List<Goods> tmps = goodsService.search(id, "", "");
        if (tmps.size() == 0) {
            return goodsCategoryService.deleteById(id) ? new BaseVO() : new BaseVO(1, "未知异常！");
        } else {
            return new BaseVO(1, "该物品类别下存在物品，无法删除！");
        }
    }

}
