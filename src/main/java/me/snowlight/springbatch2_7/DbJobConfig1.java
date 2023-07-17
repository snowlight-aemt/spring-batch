package me.snowlight.springbatch2_7;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowJob;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class DbJobConfig1 {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

//    @Bean
//    public Job helloJob1() {
//        return jobBuilderFactory
//                .get("helloJOB1")
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("Step1 Execution !!");
//                    Thread.sleep(3000);
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("Step2 Execution !!");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }

    @Bean
    public Job helloJob2() {
        return jobBuilderFactory
                .get("helloJOB2")
                .start(flow())
                .next(step5())
                .end()
                .build();
    }

    @Bean
    public Flow flow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
        flowBuilder.start(step3())
                    .next(step4())
                    .end();

        return flowBuilder.build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step3 Execution !!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step4 Execution !!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step5 Execution !!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
