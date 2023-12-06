package org.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("configuration/config.xml");
        Job importCustomerFromCsvToDbJob = context.getBean("importCustomerFromCsvToDbJob", Job.class);
        Job importCustomerFromDbToCsvJob = context.getBean("importCustomerFromDbToCsvJob", Job.class);
        Job importBankFromCsvToDbJob = context.getBean("importBankFromCsvToDbJob", Job.class);

        JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);

        launchImportBankFromCsvToDbJob(jobLauncher, importBankFromCsvToDbJob);
        launchImportCustomerFromCsvToDbJob(jobLauncher, importCustomerFromCsvToDbJob);
        launchImportCustomerFromDbToCsvJob(jobLauncher, importCustomerFromDbToCsvJob);

        context.close();
    }

    private static void launchImportCustomerFromDbToCsvJob(JobLauncher jobLauncher, Job importCustomerFromDbToCsvJob) {
        try {
            JobExecution importCustomerFromDbToCsvJobExecution = jobLauncher.run(importCustomerFromDbToCsvJob, new JobParameters());
            System.out.println(importCustomerFromDbToCsvJobExecution.getStatus());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void launchImportCustomerFromCsvToDbJob(JobLauncher jobLauncher, Job importCustomerFromCsvToDbJob) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("source",
                            "src/main/resources/data/input/customers.csv")
                    .addString("destination",
                            "src/main/resources/data/imported/customers-imported.csv")
                    .toJobParameters();

            JobExecution importCustomerFromCsvToDbJobExecution = jobLauncher.run(importCustomerFromCsvToDbJob, jobParameters);
            System.out.println(importCustomerFromCsvToDbJobExecution.getStatus());


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void launchImportBankFromCsvToDbJob(JobLauncher jobLauncher, Job importBankFromCsvToDbJob) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("source",
                            "src/main/resources/data/input/banks.csv")
                    .addString("destination",
                            "src/main/resources/data/imported/banks-imported.csv")
                    .toJobParameters();

            JobExecution importBankFromCsvToDbJobExecution = jobLauncher.run(importBankFromCsvToDbJob, jobParameters);
            System.out.println(importBankFromCsvToDbJobExecution.getStatus());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}