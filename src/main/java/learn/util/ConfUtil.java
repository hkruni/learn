package learn.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfUtil {
    private static Logger logger = LoggerFactory.getLogger(ConfUtil.class);

    public static Configuration getConf() {
        try {
            Configuration config = new PropertiesConfiguration("/my.properties");
            return config;
        }
        catch (ConfigurationException e) {
            System.out.println("读取配置文件conf.properties失败:");;
            return null;
        }
    }

    public static void main(String[] args) {
        Configuration configuration = getConf();
    }
}