FROM centos:7
RUN echo "ip_resolve=4" >> /etc/yum.conf
RUN yum update -y && yum install -y java-1.8.0-openjdk && yum install kde-l10n-Chinese -y && yum install glibc-common -y
#设置字符
RUN localedef -c -f UTF-8 -i zh_CN zh_CN.utf8
ENV LC_ALL zh_CN.UTF-8
# 设置时区。这对于日志、调用链等功能能否在 TSF 控制台被检索到非常重要。
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
# 创建 jvm 监控数据采集目录, 如果没有加上此目录将无法正常提供 jvm 监控及诊断能力
RUN mkdir -p /data/tsf_apm/monitor/jvm-metrics/
ENV workdir /app/
# 其中 microservice-provider.jar 来自于第一步下载并编译的程序包，需要注意这个 jar 包要和您的 dockerfile 位于同一级目录
ENV jar microservice-provider.jar
# agentjar TencentCloudJvmMonitor-1.0-RELEASE.jar 包来自第二步下载的 jar 包, 用来提供jvm监控功能, 需要注意这个 jar 包要和您的dockerfile位于同一级目录
ENV agentjar TencentCloudJvmMonitor-1.0-RELEASE.jar
COPY target/${jar} ${workdir}
COPY ${agentjar} ${workdir}
WORKDIR ${workdir}
# tsf-consul-template-docker 用于文件配置功能，如不需要可注释掉该行
ADD tsf-consul-template-docker.tar.gz /root/
# JAVA_OPTS 环境变量的值为部署组的 JVM 启动参数，在运行时 bash 替换。使用 exec 以使 Java 程序可以接收 SIGTERM 信号。
# TSF 1.21.0 及以上版本提供 jvm 监控能力, 需要加上 gclog 和 javaagent 的配置, 如果没加上此配置将无法提供 jvm 监控能力
CMD ["sh", "-ec", "sh /root/tsf-consul-template-docker/script/start.sh; exec java -Xloggc:/data/tsf_apm/monitor/jvm-metrics/gclog.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -verbose:gc -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=8 -XX:GCLogFileSize=50M -javaagent:${workdir}/${agentjar}=hascontroller=true ${JAVA_OPTS} -jar ${jar}"]
# 如果不需要使用文件配置功能，改用下面的启动命令
# CMD ["sh", "-ec", "exec java -Xloggc:/data/tsf_apm/monitor/jvm-metrics/gclog.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -verbose:gc -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=8 -XX:GCLogFileSize=50M -javaagent:${workdir}/${agentjar}=hascontroller=true ${JAVA_OPTS} -jar ${jar}"]