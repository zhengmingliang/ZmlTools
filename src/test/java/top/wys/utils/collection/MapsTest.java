package top.wys.utils.collection;

import com.alibaba.fastjson2.JSONObject;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;

public class MapsTest extends TestCase {
   static JSONObject jsonObject = null;

    @Before
    public void before() {
        String json = "{\"pageContent\":[{\"canView\":true,\"createTime\":\"2022-10-17 10:11:10\",\"creatorId\":\"admin\",\"databaseType\":\"ORACLE\",\"dbRelationType\":0,\"driverClassName\":\"oracle.jdbc.driver.OracleDriver\",\"id\":\"2c90926b82e741ba0183e3b590c70065\",\"maxActionNum\":20,\"maxEvictableIdleTime\":900000,\"menderId\":\"admin\",\"minActionNum\":5,\"minEvictableIdleTime\":300000,\"name\":\"testsjs\",\"orgId\":\"06c88634edf241fcb077186f53b52fc8\",\"password\":\"icell\",\"port\":0,\"updateTime\":\"2022-10-17 10:11:10\",\"url\":\"jdbc:oracle:thin:@172.16.18.221:1521:orcl\",\"username\":\"icell_analyse\"},{\"canView\":true,\"createTime\":\"2021-12-30 09:24:51\",\"creatorId\":\"admin\",\"databaseType\":\"MYSQL\",\"dbRelationType\":0,\"driverClassName\":\"com.mysql.jdbc.Driver\",\"id\":\"2c90926b790d134d0179b2146a300001\",\"maxActionNum\":8,\"maxEvictableIdleTime\":30000,\"maxWaitTime\":15000,\"menderId\":\"admin\",\"minActionNum\":0,\"minEvictableIdleTime\":300,\"name\":\"BI_basic\",\"password\":\"icell\",\"port\":0,\"updateTime\":\"2022-09-23 14:17:29\",\"url\":\"jdbc:mysql://localhost:3306/report?characterEncoding=utf-8\",\"username\":\"root\"},{\"canView\":true,\"createTime\":\"2022-06-29 10:53:08\",\"creatorId\":\"admin\",\"databaseType\":\"ORACLE\",\"dbRelationType\":0,\"driverClassName\":\"oracle.jdbc.driver.OracleDriver\",\"id\":\"2c90926b81a96d5e0181ad31d68e0000\",\"maxActionNum\":20,\"maxEvictableIdleTime\":900000,\"menderId\":\"admin\",\"minActionNum\":5,\"minEvictableIdleTime\":300000,\"name\":\"tz\",\"orgId\":\"06c88634edf241fcb077186f53b52fc8\",\"password\":\"icell\",\"port\":0,\"updateTime\":\"2022-06-29 10:53:08\",\"url\":\"jdbc:oracle:thin:@172.16.18.233:1521:orcl\",\"username\":\"icell_sso\"},{\"canView\":true,\"createTime\":\"2022-06-29 10:12:41\",\"creatorId\":\"admin\",\"databaseType\":\"ORACLE\",\"dbRelationType\":0,\"driverClassName\":\"oracle.jdbc.driver.OracleDriver\",\"id\":\"8a8880b3815c766e01815c7aa0440000\",\"maxActionNum\":20,\"maxEvictableIdleTime\":900000,\"menderId\":\"admin\",\"minActionNum\":5,\"minEvictableIdleTime\":300000,\"name\":\"1\",\"orgId\":\"06c88634edf241fcb077186f53b52fc8\",\"password\":\"bjyh_spnew\",\"port\":0,\"updateTime\":\"2022-06-29 10:12:41\",\"url\":\"jdbc:oracle:thin:@localhost:1521:orcl\",\"username\":\"bjyh_spnew\"},{\"canView\":true,\"createTime\":\"2022-06-24 09:23:00\",\"creatorId\":\"admin\",\"databaseType\":\"ORACLE\",\"dbRelationType\":0,\"driverClassName\":\"oracle.jdbc.driver.OracleDriver\",\"id\":\"2c90926b818f45e80181934e231e0001\",\"maxActionNum\":20,\"maxEvictableIdleTime\":900000,\"menderId\":\"admin\",\"minActionNum\":5,\"minEvictableIdleTime\":300000,\"name\":\"test002\",\"orgId\":\"06c88634edf241fcb077186f53b52fc8\",\"password\":\"Indicator\",\"port\":0,\"updateTime\":\"2022-06-24 09:23:00\",\"url\":\"jdbc:oracle:thin:@172.16.18.221:1521:orcl\",\"username\":\"Indicator\"},{\"canView\":true,\"createTime\":\"2022-06-24 09:16:12\",\"creatorId\":\"admin\",\"databaseType\":\"ORACLE\",\"dbRelationType\":0,\"driverClassName\":\"oracle.jdbc.driver.OracleDriver\",\"id\":\"2c90926b818f45e80181901def330000\",\"maxActionNum\":20,\"maxEvictableIdleTime\":900000,\"menderId\":\"admin\",\"minActionNum\":5,\"minEvictableIdleTime\":300000,\"name\":\"test1\",\"orgId\":\"06c88634edf241fcb077186f53b52fc8\",\"password\":\"east_es\",\"port\":0,\"updateTime\":\"2022-06-24 09:16:12\",\"url\":\"jdbc:oracljdbc:oracle:thin:@172.16.18.233:1521:orcle\",\"username\":\"east_es\"},{\"canView\":true,\"createTime\":\"2022-06-20 11:46:56\",\"creatorId\":\"admin\",\"databaseType\":\"ORACLE\",\"dbRelationType\":0,\"driverClassName\":\"oracle.jdbc.driver.OracleDriver\",\"id\":\"8a8880b3817eaad201817f387b220003\",\"maxActionNum\":20,\"maxEvictableIdleTime\":900000,\"menderId\":\"admin\",\"minActionNum\":5,\"minEvictableIdleTime\":300000,\"name\":\"name1\",\"orgId\":\"06c88634edf241fcb077186f53b52fc8\",\"password\":\"icell\",\"port\":0,\"updateTime\":\"2022-06-20 11:46:56\",\"url\":\"jdbc:oracle:thin:@172.16.18.233:1521:orcl\",\"username\":\"icell_sso\"},{\"canView\":true,\"createTime\":\"2022-06-20 11:46:47\",\"creatorId\":\"admin\",\"databaseType\":\"ORACLE\",\"dbRelationType\":0,\"driverClassName\":\"oracle.jdbc.driver.OracleDriver\",\"id\":\"8a8880b3817eaad201817f3854d80002\",\"maxActionNum\":20,\"maxEvictableIdleTime\":900000,\"menderId\":\"admin\",\"minActionNum\":5,\"minEvictableIdleTime\":300000,\"name\":\"name\",\"orgId\":\"06c88634edf241fcb077186f53b52fc8\",\"password\":\"icell\",\"port\":0,\"updateTime\":\"2022-06-20 11:46:47\",\"url\":\"jdbc:oracle:thin:@172.16.18.233:1521:orcl\",\"username\":\"icell_sso\"},{\"canView\":true,\"createTime\":\"2022-06-15 15:14:28\",\"creatorId\":\"admin\",\"databaseType\":\"ORACLE\",\"dbRelationType\":0,\"driverClassName\":\"oracle.jdbc.driver.OracleDriver\",\"id\":\"2c90926b80bcf7de0180cad090c20012\",\"maxActionNum\":20,\"maxEvictableIdleTime\":900000,\"maxWaitTime\":0,\"menderId\":\"admin\",\"minActionNum\":5,\"minEvictableIdleTime\":300000,\"name\":\"indicator\",\"orgId\":\"06c88634edf241fcb077186f53b52fc8\",\"password\":\"Indicator\",\"port\":0,\"updateTime\":\"2022-06-15 15:14:28\",\"url\":\"jdbc:oracle:thin:@172.16.18.221:1521:orcl\",\"username\":\"Indicator\"},{\"canView\":true,\"createTime\":\"2022-06-13 18:25:41\",\"creatorId\":\"admin\",\"databaseType\":\"ORACLE\",\"dbRelationType\":0,\"driverClassName\":\"oracle.jdbc.driver.OracleDriver\",\"id\":\"2c90926b8147a0e501815ae940df0038\",\"maxActionNum\":20,\"maxEvictableIdleTime\":7000,\"menderId\":\"admin\",\"minActionNum\":5,\"minEvictableIdleTime\":7000,\"name\":\"hkbank\",\"orgId\":\"06c88634edf241fcb077186f53b52fc8\",\"password\":\"YS\",\"port\":0,\"updateTime\":\"2022-06-13 18:25:41\",\"url\":\"jdbc:oracle:thin:@172.16.18.226:1521/orcl11g.us.oracle.com\",\"username\":\"ys\"}],\"pageNo\":1,\"pageSize\":10,\"startRow\":0,\"totalCount\":29,\"totalPage\":3}";
        jsonObject = JSONObject.parseObject(json);
    }

    public void testToProperties() {
        Properties properties = Maps.toProperties(jsonObject);
        System.out.println(properties);
    }


    public void testVerbosePrint() {
        Maps.verbosePrint(System.out,"fastjson",jsonObject);
    }

    public void testDebugPrint() {
        Maps.debugPrint(System.out,"fastjson",jsonObject);
    }

    public void testLogInfo() {
    }

    public void testInvertMap() {
    }

    public void testSafeAddToMap() {
    }

    public void testPutAll() {
    }
}