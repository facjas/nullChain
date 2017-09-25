package org.inchain.util.log;

import com.sun.xml.internal.ws.util.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共日志输出工具 <br>
 * 该类提供了基本的日志输出。该类不可以继承。
 * 依赖于log4j2
 *
 * @author Niels
 * @date [2017年9月25日]
 */
public final class Log {
    /**
     * 日志对象
     */
    private static final Logger LOG = ((LoggerContext) LogManager.getContext(false)).getLogger(Log.class.getName());

    /**
     * 日志级别
     */
    private static final Map<String, Level> LOG_LEVELS = new HashMap<>();

    /**
     * 存放deviceId等关键信息
     */
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 不允许实例化该类
     */
    private Log() {
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * 初始化日志等级（只开放DEBUG/INFO/WARN/ERROR/FATAL 5个级别的配置）
     */
    static {
        LOG_LEVELS.put("DEBUG", Level.DEBUG);
        LOG_LEVELS.put("INFO", Level.INFO);
        LOG_LEVELS.put("WARN", Level.WARN);
        LOG_LEVELS.put("ERROR", Level.ERROR);
        LOG_LEVELS.put("FATAL", Level.FATAL);
    }

    /**
     * 提供debug级别基本的日志输出
     *
     * @param msg 需要显示的消息
     */
    public static void debug(String msg) {
        if (LOG.isDebugEnabled()) {
            String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                    : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

            LOG.debug(logContent);
        }
    }

    public static void debug(String msg,Object ...objs){
        if (LOG.isDebugEnabled()) {
            String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                    : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

            LOG.debug(logContent,objs);
        }
    }

    /**
     * 提供debug级别基本的日志输出
     *
     * @param msg       需要显示的消息
     * @param throwable 异常信息
     */
    public static void debug(String msg, Throwable throwable) {
        if (LOG.isDebugEnabled()) {
            String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                    : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

            LOG.debug(logContent, throwable);
        }
    }

    /**
     * 提供info级别基本的日志输出
     *
     * @param msg 需要显示的消息
     */
    public static void info(String msg) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.info(logContent);
    }
    public static void info(String msg,Object ...objs) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.info(logContent,objs);
    }
    /**
     * 提供info级别基本的日志输出
     *
     * @param msg       需要显示的消息
     * @param throwable 异常信息
     */
    public static void info(String msg, Throwable throwable) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.info(logContent, throwable);
    }

    /**
     * 提供warn级别基本的日志输出
     *
     * @param msg 需要显示的消息
     */
    public static void warn(String msg) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.warn(logContent);
    }
    public static void warn(String msg,Object ...objs) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.warn(logContent,objs);
    }

    /**
     * 提供warn级别基本的日志输出
     *
     * @param msg       需要显示的消息
     * @param throwable 异常信息
     */
    public static void warn(String msg, Throwable throwable) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.warn(logContent, throwable);
    }

    /**
     * 提供error级别基本的日志输出
     *
     * @param msg 需要显示的消息
     */
    public static void error(String msg) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.error(logContent);
    }
    public static void error(String msg,Object ...objs) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.error(logContent,objs);
    }

    /**
     * 提供error级别基本的日志输出
     *
     * @param msg       需要显示的消息
     * @param throwable 异常信息
     */
    public static void error(String msg, Throwable throwable) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.error(logContent, throwable);
    }

    /**
     * 提供trace级别基本的日志输出
     *
     * @param msg 需要显示的消息
     */
    public static void trace(String msg) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.trace(logContent);
    }

    /**
     * 提供trace级别基本的日志输出
     *
     * @param msg       需要显示的消息
     * @param throwable 异常信息
     */
    public static void trace(String msg, Throwable throwable) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.trace(logContent, throwable);
    }

    /**
     * 提供fatal级别基本的日志输出
     *
     * @param msg 需要显示的消息
     */
    public static void fatal(String msg) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.fatal(logContent);
    }
    public static void fatal(String msg,Object ...objs) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.fatal(logContent,objs);
    }

    /**
     * 提供fatal级别基本的日志输出
     *
     * @param msg       需要显示的消息
     * @param throwable 异常信息
     */
    public static void fatal(String msg, Throwable throwable) {
        String logContent = isStringBlank(getId()) ? (getLogTrace() + ":" + msg)
                : (getLogTrace() + "[" + getId() + "]" + ":" + msg);

        LOG.fatal(logContent, throwable);
    }

    private static boolean isStringBlank(String val) {
        return null == val || val.trim().isEmpty();
    }

    /**
     * 获取日志记录点的全路径
     *
     * @return 日志记录点的全路径
     */
    private static String getLogTrace() {
        StringBuilder logTrace = new StringBuilder();
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        if (stack.length > 1) {
            // index为3上一级调用的堆栈信息，index为1和2都为Log类自己调两次（可忽略），index为0为主线程触发（可忽略）
            StackTraceElement ste = stack[3];
            if (ste != null) {
                // 获取类名、方法名、日志的代码行数
                logTrace.append(ste.getClassName());
                logTrace.append('.');
                logTrace.append(ste.getMethodName());
                logTrace.append('(');
                logTrace.append(ste.getFileName());
                logTrace.append(':');
                logTrace.append(ste.getLineNumber());
                logTrace.append(')');
            }
        }

        return logTrace.toString();
    }

    /**
     * 设置日志级别
     *
     * @param level 日志级别
     */
    public static void setLevel(String level) {
        if (LOG_LEVELS.containsKey(level.toUpperCase())) {
            LOG.setLevel(LOG_LEVELS.get(level.toUpperCase()));
        }
    }

    /**
     * 获取当前线程16位唯一序列号
     *
     * @return 当前线程16位唯一序列号
     */
    private static String getId() {
        return THREAD_LOCAL.get();
    }

    /**
     * 设置日志流水号
     *
     * @param id 流水号
     */
    public static void setId(String id) {
        THREAD_LOCAL.set(id);
    }
}