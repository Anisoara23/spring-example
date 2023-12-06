package org.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("configuration/config.xml");
        Job importBankFromCsvToDbJob = context.getBean("importBankFromCsvToDbJob", Job.class);
        Job importCustomerFromCsvToDbJob = context.getBean("importCustomerFromCsvToDbJob", Job.class);
        Job importCustomerFromDbToCsvJob = context.getBean("importCustomerFromDbToCsvJob", Job.class);

        JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);

        try {
            JobExecution importBankFromCsvToDbJobExecution = jobLauncher.run(importBankFromCsvToDbJob, new JobParameters());
            System.out.println(importBankFromCsvToDbJobExecution.getStatus());

            JobExecution importCustomerFromCsvToDbJobExecution = jobLauncher.run(importCustomerFromCsvToDbJob, new JobParameters());
            System.out.println(importCustomerFromCsvToDbJobExecution.getStatus());

            JobExecution importCustomerFromDbToCsvJobExecution = jobLauncher.run(importCustomerFromDbToCsvJob, new JobParameters());
            System.out.println(importCustomerFromDbToCsvJobExecution.getStatus());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        context.close();
    }
}