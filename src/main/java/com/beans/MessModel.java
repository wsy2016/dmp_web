package com.beans;

import java.util.Date;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/2/26 17:25
 */
public class MessModel {

    private String name;

    private String cardNbr;

    private Double amount;

    private Integer mgsCode;


    /**
    *  1:交易,2: 查询, 3:撤销等
    * */
    private String type;

    private Date tranTime;

    public MessModel(String name, String cardNbr, Double amount, Integer mgsCode, String type, Date tranTime) {
        this.name = name;
        this.cardNbr = cardNbr;
        this.amount = amount;
        this.mgsCode = mgsCode;
        this.tranTime = tranTime;
    }

    public String createRowKey() {
        return "hh";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNbr() {
        return cardNbr;
    }

    public void setCardNbr(String cardNbr) {
        this.cardNbr = cardNbr;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getMgsCode() {
        return mgsCode;
    }

    public void setMgsCode(Integer mgsCode) {
        this.mgsCode = mgsCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTranTime() {
        return tranTime;
    }

    public void setTranTime(Date tranTime) {
        this.tranTime = tranTime;
    }
}
