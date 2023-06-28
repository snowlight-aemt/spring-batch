package me.snowlight.springbatch2_7;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

// TODO 애플리케이션 실행 시 주입 방법
//  java -jar spring-batch-2_7-0.0.1-SNAPSHOT.jar 'name=sdh' 'age(long)=22L' 'date(date)=2023/06/28' 'dollar(double)=33.4'
//  자동 설정 켜고 아래에 컴포넌트 주석

@Component
public class JobRunner implements ApplicationRunner {
    private JobLauncher jobLauncher;
    private Job job;

    @Autowired
    public JobRunner(Job job, JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                                            .addString("name", "Kim")
                                            .addDouble("dollar", 101.5)
                                            .addLong("age", 31L)
                                            .addDate("date", new Date())
                                            .toJobParameters();

        this.jobLauncher.run(this.job, jobParameters);
    }
}
