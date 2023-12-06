package org.example.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.File;

public class MoveFileTasklet implements Tasklet, InitializingBean {

    private Resource resource;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        File fileToMove = resource.getFile();
        File newFile = new File("src/main/resources/data/imported/customers-imported.csv");
        boolean moved = fileToMove.renameTo(newFile);
        if (!moved) {
            throw new IllegalStateException("Could not move file " + fileToMove.getName());
        }
        return RepeatStatus.FINISHED;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(resource, "Resource property must be set!");
    }
}
