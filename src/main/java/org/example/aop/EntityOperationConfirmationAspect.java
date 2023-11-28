package org.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.entity.FinancialProfile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Scanner;

@Aspect
@Component
public class EntityOperationConfirmationAspect {

    public static final String YES = "y";

    private final Scanner scanner;

    public EntityOperationConfirmationAspect(Scanner scanner) {
        this.scanner = scanner;
    }

    @Around("org.example.aop.Pointcuts.saveFinancialType()")
    public Object confirmEntityAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        FinancialProfile financialProfile = (FinancialProfile) joinPoint.getArgs()[0];

        System.out.printf("\n***** THE FOLLOWING FINANCIAL PROFILE IS ABOUT TO BE ADDED: %s *****%n",
                financialProfile);

        System.out.println("\n***** ARE YOU SURE THAT YOU WANT TO ADD IT(y/n)? *****");

        String add = scanner.nextLine();

        if (add.equalsIgnoreCase(YES)) {
            Object proceed = joinPoint.proceed();
            System.out.printf("\n***** THE FINANCIAL PROFILE WAS ADDED AND HAS ID = %s *****%n",
                    financialProfile.getId());
            return proceed;
        }

        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        System.out.println("\n***** ADDING WAS CANCELED *****");
        return null;
    }

    @Around("execution(* org.example.service.*Service.remove*(..))")
    public Object confirmEntityRemoval(ProceedingJoinPoint joinPoint) throws Throwable {
        String id = (String) joinPoint.getArgs()[0];
        System.out.printf("\n***** FINANCIAL PROFILE WITH THE FOLLOWING ID IS ABOUT TO BE REMOVED: %s *****%n",
                id);

        System.out.println("\n***** ARE YOU SURE THAT YOU WANT TO DELETE IT(y/n)? *****");

        String delete = scanner.nextLine();

        if (delete.equalsIgnoreCase(YES)) {
            Object proceed = joinPoint.proceed();
            System.out.printf("\n***** FINANCIAL PROFILE WITH ID = %s WAS DELETED! *****%n",
                    id);
            return proceed;
        }

        System.out.println("\n***** DELETING IS CANCELED *****");
        return null;
    }

    @Around("org.example.aop.Pointcuts.updateOperation()")
    public Object confirmEntityUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        String id = (String) joinPoint.getArgs()[0];
        BigDecimal amount = (BigDecimal) joinPoint.getArgs()[1];

        System.out.printf("\n***** FINANCIAL PROFILE AMOUNT WITH ID = %s " +
                        "IS ABOUT TO BE UPDATED TO THE FOLLOWING AMOUNT: %s *****%n",
                id,
                amount);

        System.out.println("\n***** ARE YOU SURE THAT YOU WANT TO UPDATE THE AMOUNT(y/n)? *****");

        String update = scanner.nextLine();

        if (update.equalsIgnoreCase(YES)) {
            Object proceed = joinPoint.proceed();
            System.out.printf("\n***** FINANCIAL PROFILE AMOUNT WITH ID = %s WAS UPDATED TO %s *****%n",
                    id,
                    amount);
            return proceed;
        }

        System.out.println("\n***** UPDATING IS CANCELED *****");
        return null;
    }
}
