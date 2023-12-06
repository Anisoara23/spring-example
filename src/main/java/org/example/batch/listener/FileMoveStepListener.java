package org.example.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class FileMoveStepListener implements StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(FileMoveStepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        JobParameter source = stepExecution.getJobExecution().getJobParameters().getParameters().get("source");
        JobParameter destination = stepExecution.getJobExecution().getJobParameters().getParameters().get("destination");

        logger.info("********** FILE IS ABOUT TO BE MOVED FROM {} TO {} **********", source, destination);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        JobParameter source = stepExecution.getJobExecution().getJobParameters().getParameters().get("source");
        JobParameter destination = stepExecution.getJobExecution().getJobParameters().getParameters().get("destination");

        if (stepExecution.getStatus().equals(BatchStatus.FAILED)) {
            logger.error("********** FILE COULD NOT BE MOVED FROM {} TO {} **********", source, destination);
            return ExitStatus.FAILED;
        }

        logger.info("********** FILE WAS MOVED SUCCESSFULLY FROM {} TO {} **********", source, destination);
        return ExitStatus.COMPLETED;
    }
}
