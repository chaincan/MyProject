package com.practice.basic;

import org.apache.log4j.PropertyConfigurator;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practice.quartz.PrintJobEvery3Seconds;
import com.practice.quartz.PrintJobOnMonday10;

public class Test002_quartzFrame {

	//from w3c
//	每次当scheduler执行job时，在调用其execute(…)方法之前会创建该类的一个新的实例；执行完毕，对该实例的引用就被丢弃了，实例会被垃圾回收；这种执行策略带来的一个后果是，job必须有一个无参的
//	构造函数（当使用默认的JobFactory时）；另一个后果是，在job类中，不应该定义有状态的数据属性，因为在job的多次执行中，这些属性的值不会保留。那么如何给job实例增加属性或配置呢？如何在job的
//	多次执行中，跟踪job的状态呢？答案就是:JobDataMap，JobDetail对象的一部分。
	
	//秒	   分    时	每月第几天(?)   月       每周第几天(?)  （年）  
	private static final String CRON_EXPRESSION_3SECONDS = "*/3 * * * * ?";//每隔三秒钟执行一次,?用于无指定日期
	private static final String CRON_EXPRESSION_MONDAY10 = "0 0 10 ? * MON";//每周一10点执行
	
	private static final String GROUP_NAME = "PrintJob";
	
	public static void main(String[] args) {

		PropertyConfigurator.configure("src/log4j.properties");
		Logger logger = LoggerFactory.getLogger(Test002_quartzFrame.class);
		logger.info("begin logger------------------------------------------------------");
		
		JobDetail jobDetail1 = JobBuilder.newJob(PrintJobEvery3Seconds.class)
				.withIdentity("job1", GROUP_NAME).build();
		JobDetail jobDetail2 = JobBuilder.newJob(PrintJobOnMonday10.class)
				.withIdentity("job2", GROUP_NAME).build();
		
	    Trigger trigger1 = TriggerBuilder.newTrigger()
	    		.withIdentity("trigger1", GROUP_NAME)
	    		.forJob(jobDetail1)
	    		.withSchedule(CronScheduleBuilder.cronSchedule(CRON_EXPRESSION_3SECONDS))//cron表达式
	    		.build();
	    
	    Trigger trigger2 = TriggerBuilder.newTrigger()
	    		.withIdentity("trigger2", GROUP_NAME)
	    		.forJob(jobDetail2)
	    		.withSchedule(CronScheduleBuilder.cronSchedule(CRON_EXPRESSION_MONDAY10))
	    		.build();
	    
	    SchedulerFactory factory = new StdSchedulerFactory();
		try {
			Scheduler scheduler = factory.getScheduler();
			scheduler.scheduleJob(jobDetail1, trigger1);
			scheduler.scheduleJob(jobDetail2,trigger2);
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}


//*important*
//使用quartz必须配置好log4j,Job的实现类必须是public的，不然newJob报错

//cron表达式，由六个子表达式加一个可选表达式组成
//Seconds Minutes Hours Day-of-Month Month Day-of-Week Year (optional field)

//一个完整的Cron-Expressions的例子是字符串“0 0 12？* WED“ - 这意味着”每个星期三下午12:00“。
//
//单个子表达式可以包含范围和/或列表。例如，可以用“MON-FRI”，“MON，WED，FRI”或甚至“MON-WED，SAT”代替前一个（例如“WED”）示例中的星期几字段。
//
//通配符（'* '字符）可用于说明该字段的“每个”可能的值。因此，前一个例子的“月”字段中的“”字符仅仅是“每个月”。因此，“星期几”字段中的“*”显然意味着“每周的每一天”。
//
//所有字段都有一组可以指定的有效值。这些值应该是相当明显的 - 例如秒和分钟的数字0到59，数小时的值0到23。日期可以是1-31的任何值，但是您需要注意在给定的月份中有多少天！月份可以指定为0到11之间的值，或者使用字符串JAN，FEB，MAR，APR，MAY，JUN，JUL，AUG，SEP，OCT，NOV和DEC。星期几可以指定为1到7（1 =星期日）之间的值，或者使用字符串SUN，MON，TUE，WED，THU，FRI和SAT。
//
//'/'字符可用于指定值的增量。例如，如果在“分钟”字段中输入“0/15”，则表示“每隔15分钟，从零开始”。如果您在“分钟”字段中使用“3/20”，则意味着“每隔20分钟，从三分钟开始” - 换句话说，它与“分钟”中的“3,243,43”相同领域。请注意“ / 35”的细微之处并不代表“每35分钟” - 这意味着“每隔35分钟，从零开始” - 或者换句话说，与指定“0,35”相同。
//
//'？' 字符是允许的日期和星期几字段。用于指定“无特定值”。当您需要在两个字段中的一个字段中指定某个字符而不是另一个字段时，这很有用。请参阅下面的示例（和CronTrigger JavaDoc）以进行说明。
//
//“L”字符允许用于月日和星期几字段。这个角色对于“最后”来说是短暂的，但是在这两个领域的每一个领域都有不同的含义。例如，“月”字段中的“L”表示“月的最后一天” - 1月31日，非闰年2月28日。如果在本周的某一天使用，它只是意味着“7”或“SAT”。但是如果在星期几的领域中再次使用这个值，就意味着“最后一个月的xxx日”，例如“6L”或“FRIL”都意味着“月的最后一个星期五”。您还可以指定从该月最后一天的偏移量，例如“L-3”，这意味着日历月份的第三个到最后一天。当使用'L'选项时，重要的是不要指定列表或值的范围，因为您会得到混乱/意外的结果。
//
//“W”用于指定最近给定日期的工作日（星期一至星期五）。例如，如果要将“15W”指定为月日期字段的值，则意思是：“最近的平日到当月15日”。
//
//'＃'用于指定本月的“第n个”XXX工作日。例如，“星期几”字段中的“6＃3”或“FRI＃3”的值表示“本月的第三个星期五”。





