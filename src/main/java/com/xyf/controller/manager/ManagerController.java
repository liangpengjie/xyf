package com.xyf.controller.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.*;
import com.xyf.entity.ImgsUrl;
import com.xyf.entity.ProductsLog;
import com.xyf.entity.ShipLog;
import com.xyf.entity.manager.ManagerUser;
import com.xyf.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.InputStream;
import java.util.Map;


@RestController
@RequestMapping(value = "web/api/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerServiceService;

    /**
     * 添加管理员
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public MyResponse add(@RequestBody @Valid ManagerDTO dto) {
        return managerServiceService.add(dto);
    }


    /**
     * 账号密码登录
     * @param dto
     * @return
     */
    @PostMapping("/login")
    public MyResponse login(@RequestBody @Valid ManagerDTO dto) {
        return managerServiceService.login(dto);
    }

    /**
     * 修改密码
     * @param dto
     * @return
     */
    @PostMapping("/edit")
    public MyResponse edit(@RequestBody @Valid ManagerDTO dto) {
        return managerServiceService.edit(dto);
    }

    /**
     * 删除管理员
     * @return
     */
    @PostMapping("/delete")
    public MyResponse delete(@RequestBody ManagerUser manager) {
        return managerServiceService.delete(manager);
    }




    /**
     * 后台录入数据合伙人激活
     * @param dto
     * @return
     */
    @PostMapping("/initPartner")
    public MyResponse initPartner(@RequestBody @Valid InitPartnerDTO dto) {
        return managerServiceService.initPartner(dto);
    }

    /**
     * 用户办卡成功返现奖励
     * @param dto
     * @return
     */
    @PostMapping("/initCreateCard")
    public MyResponse initCreateCard(@RequestBody @Valid ListDTO dto) {
        return managerServiceService.initCreateCard(dto);
    }

    /**
     * 后台录入刷卡奖励
     * @param dto
     * @return
     */
    @PostMapping("/initUserCradBonus")
    public MyResponse initUserCradBonus(@RequestBody @Valid ListDTO dto) {
        return managerServiceService.initUserCradBonus(dto);
    }


    /**
     * 根据当前用户查看直属下级
     * @param map
     * @return
     */
    @PostMapping("/selectSubordinate")
    public MyResponse selectSubordinate(@RequestBody Map map) {
        return managerServiceService.selectSubordinate(map);
    }


    /**
     * 合伙人登陆后台
     * @param dto
     * @return
     */
    @PostMapping("/userLogin")
    public MyResponse userLogin(@RequestBody @Valid LoginDTO dto) {
        return managerServiceService.userLogin(dto);
    }


    /**
     * 购买 pos 机
     * @param productsLog
     * @return
     */
    @PostMapping("/buyPosji")
    public MyResponse buyPosji(@RequestBody @Valid ProductsLog productsLog) {
        return managerServiceService.buyPosji(productsLog);
    }

    /**
     * 可激活合伙人用户列表
     * @return
     */
    @PostMapping("/buyPosjiUserList")
    public MyResponse buyPosjiUserList(@RequestBody @Valid StatusDTO dto) {
        return managerServiceService.buyPosjiUserList(dto);
    }

    /**
     * 购买 POS 机发货列表
     * @return
     */
    @PostMapping("/shipList")
    public MyResponse shipList(@RequestBody @Valid StatusDTO dto) {
        return managerServiceService.shipList(dto);
    }

    /**
     * pos 发货状态改变
     * @return
     */
    @PostMapping("/shipstatus")
    public MyResponse shipstatus(@RequestBody @Valid TYIDDTO dto) {
        return managerServiceService.shipstatus(dto);
    }

    /**
     * 平台收益记录
     * @return
     */
    @PostMapping("/ptuy")
    public MyResponse ptuy(@RequestBody Page page) {
        return managerServiceService.ptuy(page);
    }

    /**
     * 用户收益记录
     * @return
     */
    @PostMapping("/yhuy")
    public MyResponse yhuy(@RequestBody@Valid IdDTO dto) {
        return managerServiceService.ysuy(dto);
    }

    /**
     * 用户刷卡记录
     * @return
     */
    @PostMapping("/yhUseCard")
    public MyResponse yhUseCard(@RequestBody@Valid IdDTO dto) {
        return managerServiceService.yhUseCard(dto);
    }


    /**
     * 后台查看提现记录
     * @param shipLogPageDTO
     * @return
     */
    @PostMapping("/shipLogList")
    public MyResponse shipLogList(@RequestBody @Valid ShipLogPageDTO shipLogPageDTO) {
        return managerServiceService.shipLogList(shipLogPageDTO);
    }

    /**
     * 更改提现状态
     * @param
     * @return
     */
    @PostMapping("/editShipLogStatus")
    public MyResponse editShipLogStatus(@RequestBody @Valid TYIDDTO id) {
        return managerServiceService.editShipLogStatus(id);
    }

    /**
     * 图片地址
     * @param
     * @return
     */
    @PostMapping("/imgsUrlList")
    public MyResponse imgsUrlList() {
        return managerServiceService.imgsUrlList();
    }

    /**
     * 修改图片地址
     * @param
     * @return
     */
    @PostMapping("/editImgsUrl")
    public MyResponse editImgsUrl(@RequestBody @Valid ImgsUrl imgsUrl) {
        return managerServiceService.editImgsUrl(imgsUrl);
    }

    //文件上传相关代码
    @RequestMapping(value = "uploadExcel")
    @ResponseBody
    public MyResponse upload(@RequestBody MultipartFile file) {
        if (file.isEmpty()) {
            return new MyResponse("文件为空", 0);
        }

        return managerServiceService.excel(file);
    }
}
