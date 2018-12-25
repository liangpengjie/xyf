package com.xyf.entity;

/**
 * @author lmumuj
 * @date 2018-12-20 16:02
 */
public class Transaction  {

    // @ExcelProperty(index = 5)
    private Double tranMoney;

    // @ExcelProperty(index = 7)
    // 交易日期
    private String tranDate;

    // @ExcelProperty(index = 10)
    // 终端号
    private String terminalNumber;

    // @ExcelProperty(index = 12)
    // 交易结果
    private String success;

    public Transaction() {
    }

    public Transaction(Double tranMoney, String tranDate, String terminalNumber, String success) {
        this.tranMoney = tranMoney;
        this.tranDate = tranDate;
        this.terminalNumber = terminalNumber;
        this.success = success;
    }

    public Double getTranMoney() {
        return tranMoney;
    }

    public void setTranMoney(Double tranMoney) {
        this.tranMoney = tranMoney;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
