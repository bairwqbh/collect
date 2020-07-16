package per.cby.collect.orbcomm.task;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import per.cby.collect.orbcomm.service.OrbcommService;
import per.cby.frame.common.constant.DefaultValue;
import per.cby.frame.task.annotation.ElasticJobTask;
import per.cby.frame.task.model.Task;
import per.cby.frame.task.scheduler.SimpleElasticJobScheduler;
import per.cby.frame.task.service.TaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * OGI消息轮询任务
 * 
 * @author chenboyang
 * @since 2020年2月13日
 *
 */
@Slf4j
@ElasticJobTask(
    name = "orbcomm.message.poll",
    cron = "0/30 * * * * ? *",
    listener = OrbcommMessagePollListener.class,
    description = "OGI消息轮询任务"
)
public class OrbcommMessagePollJob extends SimpleElasticJobScheduler {

    @Autowired(required = false)
    private TaskService taskService;

    @Autowired(required = false)
    private OrbcommService orbcommService;

    @Override
    public void execute(ShardingContext context) {
        Task task = taskService.getDefault(context.getJobName());
        if (DefaultValue.FIRST_DATE_TIME.equals(task.getStartTime())) {
            task.setStartTime(LocalDate.now().atStartOfDay());
        }
        orbcommService.pollMessage(task.getStartTime());
        log.debug("任务名称：{}，开始时间：{}，总分片数：{}，当前分片：{}", context.getJobName(), task.getStartTime(),
                context.getShardingTotalCount(), context.getShardingItem());
    }

}
