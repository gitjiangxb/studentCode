package batch.service;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class JobLauncherService {

    @Autowired
    private JobLauncher jobLauncher;

    public Map<String, Object> startJob(Job job, JobParameters jobParameters) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, Object> resultMap = new HashMap<>();
        //计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(job.getName());
        //执行任务
        JobExecution run = jobLauncher.run(job, jobParameters);
        //返回结果
        StringBuffer stringBuffer = new StringBuffer();
        Collection<StepExecution> stepExecutions = run.getStepExecutions();
        stepExecutions.forEach(stepExecution -> {
            stringBuffer.append("readCount:" + stepExecution.getCommitCount() + ",");
            stringBuffer.append("filterCount:" + stepExecution.getFilterCount() + ",");
            stringBuffer.append("commitCount:" + stepExecution.getCommitCount() + ",");
            stringBuffer.append("writeCount:" + stepExecution.getWriteCount());
        });
        stopWatch.stop();
        ExitStatus exitStatus = run.getExitStatus();
        String returnStr = System.lineSeparator() + "resultCount: " + stringBuffer.toString()
                + System.lineSeparator() + "exitStatus: " + exitStatus
                + System.lineSeparator() + "timeInfo: " + stopWatch.prettyPrint();
        System.out.println(returnStr);
        resultMap.put("resultStr", returnStr);
        resultMap.put("exitStatus", exitStatus);
        return resultMap;
    }

}
