package edu.school21.tanks.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources({
		@PropertySource("/game.properties"),
		@PropertySource("/db.properties")
})
@ComponentScan(basePackages = "edu.school21.tanks")
public class ApplicationConfig {
	@Value("${db.url}")
	private String url;
	@Value("${db.user}")
	private String user;
	@Value("${db.password}")
	private String password;
	@Value("${db.driver.name}")
	private String driverName;

	@Bean
	public HikariDataSource hikariDataSource() {
		HikariConfig hikariConfig = new HikariConfig();

		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(user);
		hikariConfig.setPassword(password);
		hikariConfig.setDriverClassName(driverName);

		return new HikariDataSource(hikariConfig);
	}
}
