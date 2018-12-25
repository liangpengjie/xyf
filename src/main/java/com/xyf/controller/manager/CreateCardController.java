package com.xyf.controller.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.CreateCardInfoDTO;
import com.xyf.dto.Page;
import com.xyf.dto.TYIDDTO;
import com.xyf.service.manager.CreateCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/web/api/createCard")
public class CreateCardController {

    @Autowired
    private CreateCardService createCardService;

    /**
     * 立即办卡记录用户信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/createCardInfo")
    public MyResponse createCardInfo(@RequestBody @Valid CreateCardInfoDTO dto) {
        return createCardService.CreateCardInfo(dto);

    }

    /**
     * 办卡用户信息列表
     *
     * @return
     */
    @PostMapping("/list")
    public MyResponse createCardInfoList(@RequestBody Page page) {
        return createCardService.createCardInfoList(page);
    }

    /**
     * 删除办卡信息
     *
     * @return
     */
    @PostMapping("/delete")
    public MyResponse delete(@RequestBody @Valid TYIDDTO dto) {
        return createCardService.delete(dto);
    }
}
