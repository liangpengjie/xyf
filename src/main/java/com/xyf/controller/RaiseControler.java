package com.xyf.controller;

import com.xyf.common.MyResponse;
import com.xyf.dto.Page;
import com.xyf.dto.PageDTO;
import com.xyf.entity.Raise;
import com.xyf.service.manager.RaiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmumuj
 * @date 2018-12-19 18:37
 */
@RestController
@RequestMapping("/web/api/raise")
public class RaiseControler {
    @Autowired
    private RaiseService raiseService;

    @PostMapping("list")
    public MyResponse get(@RequestBody Page pageDTO){
        return raiseService.list(pageDTO);
    }
    @PostMapping("add")
    public MyResponse add(@RequestBody Raise raise){
        return raiseService.add(raise);
    }
    @PostMapping("edit")
    public MyResponse edit(@RequestBody Raise raise){
        return raiseService.edit(raise);
    }
    @PostMapping("del")
    public MyResponse del(@RequestBody Raise raise){
        return raiseService.del(raise);
    }


}
