package com.sunll.center.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {
    /**   id **/
    private Integer id;

    /** 帐户余额  balance **/
    private BigDecimal balance;

    /** 套餐余额  set_meal_remaind **/
    private BigDecimal setMealRemaind;

    /**   tableName: account   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   帐户余额  balance   **/
    public BigDecimal getBalance() {
        return balance;
    }

    /**   帐户余额  balance   **/
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**   套餐余额  set_meal_remaind   **/
    public BigDecimal getSetMealRemaind() {
        return setMealRemaind;
    }

    /**   套餐余额  set_meal_remaind   **/
    public void setSetMealRemaind(BigDecimal setMealRemaind) {
        this.setMealRemaind = setMealRemaind;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", balance=").append(balance);
        sb.append(", setMealRemaind=").append(setMealRemaind);
        sb.append("]");
        return sb.toString();
    }
}