package com.lazy.sentinel.admin.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.lazy.cheetah.core.helper.ReadPropertyByStreamHelper;
import com.lazy.cheetah.core.shiro.SysDefaultShiroFormAuthenticationFilter;
import com.lazy.cheetah.core.shiro.SysDefaultShiroHashedCredentialsMatcher;
import com.lazy.cheetah.core.shiro.SysDefaultShiroRedisSessionDao;
import com.lazy.sentinel.admin.shiro.SysDefaultShiroRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Shiro 配置</p>
 *
 * @author laizhiyuan
 * @date 2018/3/25.
 */
@SuppressWarnings("all")
@Configuration
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "admin-test")
public class ShiroTestEnvConfig {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroTestEnvConfig.class);


    /**
     * 授权认证实现
     *
     * @return
     */
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public SysDefaultShiroRealm systemDefaultRealm() {
        LOGGER.info("init bean systemDefaultRealm");
        SysDefaultShiroRealm sysDefaultShiroRealm = new SysDefaultShiroRealm();
        sysDefaultShiroRealm.setAuthenticationCacheName("lazy-sentinel-authenticationCache");
        sysDefaultShiroRealm.setAuthorizationCacheName("lazy-sentinel-authorizationCache");
        sysDefaultShiroRealm.setAuthenticationCachingEnabled(true);
        sysDefaultShiroRealm.setAuthorizationCachingEnabled(true);
        sysDefaultShiroRealm.setCredentialsMatcher(shiroHashedCredentialsMatcher());
        return sysDefaultShiroRealm;
    }
    /**
     * Shiro 凭证Hash管理器
     *
     * @return
     */
    @Bean
    public SysDefaultShiroHashedCredentialsMatcher shiroHashedCredentialsMatcher() {
        SysDefaultShiroHashedCredentialsMatcher matcher = new SysDefaultShiroHashedCredentialsMatcher();
        //散列算法:这里使用MD5算法
        matcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        matcher.setHashIterations(2);
        matcher.setPasswordRetryCache(ehCacheManage().getCache("lazy-sentinel-passwordRetryCache"));
        Integer retriesCount = Integer.valueOf(ReadPropertyByStreamHelper.getInstance().getByEnvProp("app.retriesCount", false, "3"));
        LOGGER.info("init bean shiroHashedCredentialsMatcher");
        LOGGER.info("retriesCount:" + retriesCount);
        matcher.setRetries(retriesCount);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }


    /**
     * 会话id生成器
     *
     * @return
     */
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        LOGGER.info("init bean sessionIdGenerator");
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * Cookie模板
     *
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie() {
        LOGGER.info("init bean simpleCookie");
        SimpleCookie cookie = new SimpleCookie("lazy-sentinel-sid");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Integer.valueOf(ReadPropertyByStreamHelper.getInstance().getByEnvProp("app.readMeCookieMaxAge", false, "259200")));
        return cookie;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{securityManager()});
        return factoryBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    /**
     * 会话DAO
     *
     * @return
     */
    @Bean
    public SysDefaultShiroRedisSessionDao redisSessionDao() {
        LOGGER.info("init bean redisSessionDao");
        SysDefaultShiroRedisSessionDao sessionDao = new SysDefaultShiroRedisSessionDao();
//        sessionDao.setActiveSessionsCacheName("lazy-sentinel-activeSessionCache");
        sessionDao.setSessionIdGenerator(sessionIdGenerator());
        return sessionDao;
    }

    /**
     * 会话DAO 支持二级缓存
     *
     * @return
     */
//    @Bean
//    public SysDefaultShiroSessionCacheRedisDao redisSessionDao() {
//        LOGGER.info("init bean redisSessionDao");
//        SysDefaultShiroSessionCacheRedisDao sessionDao = new SysDefaultShiroSessionCacheRedisDao();
//        sessionDao.setActiveSessionsCacheName("lazy-sentinel-activeSessionCache");
//        sessionDao.setCacheManager(ehCacheManage());
//        sessionDao.setSessionIdGenerator(sessionIdGenerator());
//        return sessionDao;
//    }

    /**
     * EhCache 缓存管理器
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManage() {
        LOGGER.info("init bean ehCacheManage");
        EhCacheManager ehCacheManager = new EhCacheManager();
        String ehCacheXmlPath = ReadPropertyByStreamHelper.getInstance().getByEnvProp("ehcache.xml.path", true, null);
        ehCacheManager.setCacheManagerConfigFile(ehCacheXmlPath);
        return ehCacheManager;
    }

    /**
     * Redis 缓存管理器
     *
     * @return
     */
//    @Bean
//    public SysDefaultShiroRedisCacheManager redisCacheManage() {
//        LOGGER.info("init bean redisCacheManage");
//        return new SysDefaultShiroRedisCacheManager();
//    }

    /**
     * 定时验证会话是否过期
     * @return
     */
//    @Bean(name = "sessionValidationScheduler")
//    public ExecutorServiceSessionValidationScheduler sessionValidationScheduler() {
//        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
//        String globalSessionTimeout = ReadPropertyByStreamHelper.getInstance().getByEnvProp("app.sessionExpiration", true, null);
//        scheduler.setInterval(Long.valueOf(globalSessionTimeout));
//        return scheduler;
//    }

    /**
     * 会话管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        LOGGER.info("init bean sessionManager");
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDao());
        sessionManager.setDeleteInvalidSessions(true);
        String globalSessionTimeout = ReadPropertyByStreamHelper.getInstance().getByEnvProp("app.sessionExpiration", true, null);
        sessionManager.setGlobalSessionTimeout(Long.valueOf(globalSessionTimeout) * 1000L);
        sessionManager.setCacheManager(ehCacheManage());
        sessionManager.setSessionIdCookieEnabled(true);
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setSessionValidationScheduler(sessionValidationScheduler());
        sessionManager.setSessionIdCookie(simpleCookie());
        return sessionManager;
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
//        securityManager.setRememberMeManager(rememberMeManager());
//        securityManager.setCacheManager(redisCacheManage());
        securityManager.setCacheManager(ehCacheManage());
        securityManager.setRealm(systemDefaultRealm());
        return securityManager;
    }

    /**
     * Advisor AOP
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        LOGGER.info("init bean authorizationAttributeSourceAdvisor");
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    /**
     * 登录过滤器
     *
     * @return
     */
    public FormAuthenticationFilter formAuthenticationFilter() {
        LOGGER.info("init bean formAuthenticationFilter");
        SysDefaultShiroFormAuthenticationFilter formAuthenticationFilter = new SysDefaultShiroFormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("loginName");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        formAuthenticationFilter.setSuccessUrl("/index");
        return formAuthenticationFilter;
    }

    /**
     * 登出过滤器
     * @return
     */
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login");
        return logoutFilter;
    }

  /**
     * 定义过滤器链
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        LOGGER.info("init bean shiroFilter");
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager());
        Map<String, Filter> filters = filterFactoryBean.getFilters();
        filters.put("authc", formAuthenticationFilter());
        filters.put("logout", logoutFilter());
        filterFactoryBean.setFilters(filters);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        this.appendPermissionFilterChain(filterChainDefinitionMap);
        filterChainDefinitionMap.put("/error/**", "anon");
        filterChainDefinitionMap.put("/welcome/**", "anon");
        filterChainDefinitionMap.put("/layuiadmin/**", "anon");
        filterChainDefinitionMap.put("/api_doc/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/map/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/datakanban/**", "anon");

        filterChainDefinitionMap.put("/unauthorized/**", "anon");
        filterChainDefinitionMap.put("/demos/**", "anon");
        filterChainDefinitionMap.put("/kaptcha/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/logout", "logout");

        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setUnauthorizedUrl("/unauthorized");
        filterFactoryBean.setSuccessUrl("/index");

        filterChainDefinitionMap.put("/**", "authc");
        LOGGER.info("InitPermissionFilterChain: " + filterChainDefinitionMap.toString());
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }

    /**
     * 追加权限过滤器链
     *
     * @param filterChainDefinitionMap
     */
    private void appendPermissionFilterChain(LinkedHashMap<String, String> filterChainDefinitionMap) {
        String driverName = ReadPropertyByStreamHelper.getInstance().getByEnvProp("spring.datasource.driverClassName", true, null);
        String url = ReadPropertyByStreamHelper.getInstance().getByEnvProp("spring.datasource.url", true, null);
        String user = ReadPropertyByStreamHelper.getInstance().getByEnvProp("spring.datasource.username", true, null);
        String password = ReadPropertyByStreamHelper.getInstance().getByEnvProp("spring.datasource.password", false, "");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select t.uri, t.res_code from t_permissions_resource t where 1=1 and t.res_type = 'uri'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                filterChainDefinitionMap.put(rs.getString("uri"), "authc,perms[" + rs.getString("res_code") + "]");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    /**
     * 管理Shiro Bean生命周期
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     *
     * kaptcha 验证码配置

     spring.kaptcha.properties.kaptcha.textproducer.char.string=0123456789ABCDEFGHIJKLMNPQRSTUVWXYZ
     spring.kaptcha.properties.kaptcha.textproducer.char.length=4
     spring.kaptcha.properties.kaptcha.border=no
     spring.kaptcha.properties.kaptcha.image.width=80
     spring.kaptcha.properties.kaptcha.image.height=35
     spring.kaptcha.properties.kaptcha.border.color=105,179,90
     spring.kaptcha.properties.kaptcha.textproducer.font.color=blue
     spring.kaptcha.properties.kaptcha.textproducer.font.size=30
     spring.kaptcha.properties.kaptcha.textproducer.font.names=宋体,宋体,宋体,宋体
     spring.kaptcha.properties.kaptcha.obscurificator.impl=com.google.code.kaptcha.impl.WaterRipple
     spring.kaptcha.properties.kaptcha.session.key=code
     spring.kaptcha.properties.kaptcha.noise.color=white
     spring.kaptcha.properties.kaptcha.background.clear.from=white
     spring.kaptcha.properties.kaptcha.background.clear.to=white

     #	kaptcha.border  是否有边框  默认为true  我们可以自己设置yes，no
     #	kaptcha.border.color   边框颜色   默认为Color.BLACK
     #	kaptcha.border.thickness  边框粗细度  默认为1
     #	kaptcha.producer.impl   验证码生成器  默认为DefaultKaptcha
     #	kaptcha.textproducer.impl   验证码文本生成器  默认为DefaultTextCreator
     #	kaptcha.textproducer.char.string   验证码文本字符内容范围  默认为abcde2345678gfynmnpwx
     #	kaptcha.textproducer.char.length   验证码文本字符长度  默认为5
     #	kaptcha.textproducer.font.names    验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
     #	kaptcha.textproducer.font.size   验证码文本字符大小  默认为40
     #	kaptcha.textproducer.font.color  验证码文本字符颜色  默认为Color.BLACK
     #	kaptcha.textproducer.char.space  验证码文本字符间距  默认为2
     #	kaptcha.noise.impl    验证码噪点生成对象  默认为DefaultNoise
     #	kaptcha.noise.color   验证码噪点颜色   默认为Color.BLACK
     #	kaptcha.obscurificator.impl   验证码样式引擎  默认为WaterRipple
     #	kaptcha.word.impl   验证码文本字符渲染   默认为DefaultWordRenderer
     #	kaptcha.background.impl   验证码背景生成器   默认为DefaultBackground
     #	kaptcha.background.clear.from   验证码背景颜色渐进   默认为Color.LIGHT_GRAY
     #	kaptcha.background.clear.to   验证码背景颜色渐进   默认为Color.WHITE
     #	kaptcha.image.width   验证码图片宽度  默认为200
     #	kaptcha.image.height  验证码图片高度  默认为50

     * @return
     */
    @Bean
    public DefaultKaptcha captchaProducer(){
        DefaultKaptcha captchaProducer =new DefaultKaptcha();
        Properties properties =new Properties();
        properties.setProperty("kaptcha.border","yes");
        properties.setProperty("kaptcha.border.color","105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color","blue");
        properties.setProperty("kaptcha.image.width","125");
        properties.setProperty("kaptcha.image.height","45");
        properties.setProperty("kaptcha.textproducer.font.size","45");
        properties.setProperty("kaptcha.session.key","code");
        properties.setProperty("kaptcha.textproducer.char.length","4");
        properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
        Config config=new Config(properties);
        captchaProducer.setConfig(config);
        return  captchaProducer;
    }

}
