package me.snowlight.springbatch2_7;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobRepositoryListener implements JobExecutionListener {
    private final JobRepository jobRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestData", "20230711")
                .toJobParameters();

        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);

        if (lastJobExecution != null) {
            for (StepExecution stepExecution : lastJobExecution.getStepExecutions()) {
                System.out.println(stepExecution.getStatus());
                System.out.println(stepExecution.getExitStatus());
                System.out.println(stepExecution.getStepName());
            }
        }
    }
}
