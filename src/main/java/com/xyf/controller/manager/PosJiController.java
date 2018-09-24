package com.xyf.controller.manager;

import com.xyf.common.MyResponse;
import com.xyf.entity.PosJi;
import com.xyf.service.manager.PosJiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/api/podji")
public class PosJiController {

    @Autowired
    private PosJiService posJiService;


    @PostMapping("/add")
    public MyResponse add(@RequestBody PosJi posJi) {
        return posJiService.add(posJi);
    }

    @PostMapping("/edit")
    public MyResponse edit(@RequestBody PosJi posJi) {
        return posJiService.edit(posJi);
    }

    @PostMapping("/delete")
    public MyResponse delete(@RequestBody PosJi posJi) {
        return posJiService.delete(posJi);
    }


    @PostMapping("/list")
    public MyResponse list() {
        return posJiService.list();
    }

    @PostMapping("/selectById")
    public MyResponse selectById(@RequestBody PosJi posJi) {
        return posJiService.selectById(posJi);
    }
}
