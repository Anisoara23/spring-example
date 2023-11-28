package org.example.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("args(org.example.entity.FinancialProfile)")
    public void isFinancialType(){}

    @Pointcut("execution(* org.example.service.*Service.add(..))")
    public void saveOperation(){}

    @Pointcut("isFinancialType() && saveOperation()")
    public void saveFinancialType(){}

    @Pointcut("execution(* org.example.service.*Service.remove*(..))")
    public void removeOperation(){}

    @Pointcut("execution(* org.example.service.FinancialProfileService.updateAmount(..))")
    public void updateOperation(){}
}
