package com.lujiatao.ims.web.controller;

import com.lujiatao.ims.api.GoodsService;
import com.lujiatao.ims.common.entity.Goods;
import com.lujiatao.ims.common.model.BaseVO;
import com.lujiatao.ims.web.util.Paginationer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
@ResponseBody
public class GoodsController {

    @DubboReference(version = "1.0.0")
    private GoodsService goodsService;
    private final Paginationer paginationer = new Paginationer();

    @GetMapping("/all")
    public BaseVO all() {
        List<Goods> list = goodsService.getAll();
        Object[] objects = paginationer.getTargetDatas(list, 1);
        return new BaseVO(objects);
    }

    @GetMapping("/page")
    public BaseVO page(@RequestParam("goodsCategoryId") int goodsCategoryId, @RequestParam("brand") String brand, @RequestParam("model") String model, @RequestParam("targetPage") int targetPage) {
        List<Goods> list = goodsService.search(goodsCategoryId, brand, model);
        Object[] objects = paginationer.getTargetDatas(list, targetPage);
        return new BaseVO(objects);
    }

    @GetMapping("/search")
    public BaseVO search(@RequestParam("goodsCategoryId") int goodsCategoryId, @RequestParam("brand") String brand, @RequestParam("model") String model) {
        List<Goods> list = goodsService.search(goodsCategoryId, brand, model);
        Object[] objects = paginationer.getTargetDatas(list, 1);
        return new BaseVO(objects);
    }

    @PostMapping
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO add(@RequestBody Goods goods) {
        int id = goods.getId();
        Goods tmp = goodsService.getById(id);
        if (tmp == null) {
            return goodsService.add(goods) ? new BaseVO() : new BaseVO(1, "未知异常！");
        } else {
            return new BaseVO(1, "该记录已存在！");
        }
    }

    @PutMapping
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO modify(@RequestBody Goods goods) {
        int id = goods.getId();
        Goods tmp = goodsService.getById(id);
        if (tmp != null) {
            int count = goods.getCount();
            if (count == 0) {//编辑
                goods.setCount(tmp.getCount());
                return goodsService.modify(goods) ? new BaseVO() : new BaseVO(1, "未知异常！");
            } else {//出入库
                int newCount = tmp.getCount() + count;
                if (newCount < 0) {
                    return new BaseVO(1, "库存不足！");
                }
                tmp.setCount(newCount);
                return goodsService.modify(tmp) ? new BaseVO() : new BaseVO(1, "未知异常！");
            }
        } else {
            return new BaseVO(1, "该记录不存在！");
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO delete(@RequestParam("id") int id) {
        Goods tmp = goodsService.getById(id);
        if (tmp != null) {
            return goodsService.deleteById(id) ? new BaseVO() : new BaseVO(1, "未知异常！");
        } else {
            return new BaseVO(1, "该记录不存在！");
        }
    }

}
