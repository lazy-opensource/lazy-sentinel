package com.lazy.sentinel.admin;

import com.lazy.cheetah.core.factory.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author laizhiyuan
 * @date 2018/1/8.
 * <p>Spring Boot 启动主Main类</p>
 */
@SpringBootApplication
@ComponentScan(value={"com.lazy"})
@EntityScan({"com.lazy"})
@EnableJpaRepositories(
		value = {"com.lazy.**.repository", "com.lazy.**.dao"},
		repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class
)
public class LazySentinelAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(LazySentinelAdminApplication.class, args);
	}
}
