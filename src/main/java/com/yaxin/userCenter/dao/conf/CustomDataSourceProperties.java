package com.yaxin.userCenter.dao.conf;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 获取第数据库的连接信息，在application.properties中配置，
 * 并指定特定的前缀 比如 url 在配置文件中对应的就是 lxy.datasource.url
 *
 */
@Component
@ConfigurationProperties(prefix = CustomDataSourceProperties.PREFIX)
public class CustomDataSourceProperties implements BeanClassLoaderAware{
	
	public static final String PREFIX = "jdbc.datasource";
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private ClassLoader classLoader;
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the driverClassName
	 */
	public String getDriverClassName() {
		return driverClassName;
	}
	/**
	 * @param driverClassName the driverClassName to set
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	/**
	 * @return the classLoader
	 */
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		
	}
	
	
}