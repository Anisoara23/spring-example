package org.example.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class FileMoveStepListener implements StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(FileMoveStepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("********** FILE IS ABOUT TO BE MOVED **********");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getStatus().equals(BatchStatus.FAILED)) {
            logger.error("********** FILE COULD NOT BE MOVED **********");
            return ExitStatus.FAILED;
        }

        logger.info("********** FILE IS MOVED SUCCESSFULLY **********");
        return ExitStatus.COMPLETED;
    }
}
