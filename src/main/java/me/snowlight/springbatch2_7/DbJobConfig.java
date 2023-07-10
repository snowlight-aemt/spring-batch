package me.snowlight.springbatch2_7;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class DbJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory
                .get("helloJOB")
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }
    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get("step1")
                .tasklet(new CustomTasklet())
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory
                .get("step2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("# STEP2 has executed !!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory
                .get("step3")
                .tasklet((contribution, chunkContext) -> {
                    log.info("# STEP3 has executed !!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
