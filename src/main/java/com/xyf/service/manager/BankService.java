package com.xyf.service.manager;

import com.xyf.common.MyResponse;
import com.xyf.dto.DeleteBankDTO;
import com.xyf.entity.manager.Bank;

public interface BankService {
    /**
     * 添加银行
     * @param bank
     * @return
     */
    MyResponse add(Bank bank);

    /**
     * 修改银行
     * @param bank
     * @return
     */
    MyResponse edit(Bank bank);

    /**
     * 删除银行
     * @return
     */
    MyResponse delete(DeleteBankDTO dto);

    /**
     * 查看所有银行
     * @return
     */
    MyResponse list();

}
