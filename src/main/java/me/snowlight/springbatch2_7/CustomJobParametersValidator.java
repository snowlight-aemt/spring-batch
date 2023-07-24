package me.snowlight.springbatch2_7;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class CustomJobParametersValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        if (parameters.getString("date1") == null) {
            throw new JobParametersInvalidException("파라미터 date1 가 없습니다.");
        }
    }
}
