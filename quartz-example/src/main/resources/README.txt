spring集成定时任务的3种方式：
1.xml配置文件方式：配置jobDetal,配置trigger,再配置调度工厂，启动任务，
    其中jibDetail引入了自己定义的job类，引导最终执行的任务方法（属于quartz）
2.注解方式：首先配置支持注解<task:annotation-driven/>，再使用@Scheduled，标注任务方法(属于spring)
3.将任务存到数据库：在项目启动的时候通过监听器注册定时任务，
    查询定时任务->创建jobDetail以及trigger->用Scheduler启动任务（属于quartz）