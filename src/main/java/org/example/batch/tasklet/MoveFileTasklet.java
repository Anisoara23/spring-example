package org.example.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.File;

public class MoveFileTasklet implements Tasklet, InitializingBean {

    private Resource source;

    private Resource destination;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        File fileToMove = source.getFile();
        File movedFile = destination.getFile();
        boolean moved = fileToMove.renameTo(movedFile);
        if (!moved) {
            throw new IllegalStateException("Could not move file " + fileToMove.getName());
        }
        return RepeatStatus.FINISHED;
    }

    public Resource getSource() {
        return source;
    }

    public void setSource(Resource source) {
        this.source = source;
    }

    public Resource getDestination() {
        return destination;
    }

    public void setDestination(Resource destination) {
        this.destination = destination;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(source, "Source property must be set!");
        Assert.notNull(destination, "Destination property must be set!");
    }
}
