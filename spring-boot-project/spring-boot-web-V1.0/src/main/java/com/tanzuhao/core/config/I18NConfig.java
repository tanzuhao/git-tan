package com.tanzuhao.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 国际化配置类,定义资源包消息源Bean
 * 
 * i18n（其来源是英文单词 internationalization的首末字符i和n，18为中间的字符数）是“国际化”的简称
 * 
 * setBaseName设置消息源的文件名，messageSource.setBasename("application");，
 * 表明消息源是以applicition打头的属性文件，如果要设置多个属性文件作为消息源，那么就要用setBaseNames方法来设置，比如：
 * messageSource.setBasenames("student", "application");
 * 这样就有两个消息源：student.properties和application.properties。
 * 
 * @author tanzuhao
 *
 */
@Configuration
public class I18NConfig {
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setFallbackToSystemLocale(false);
		// 设置消息源的文件名,
		messageSource.setBasename("application");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(2);
		return messageSource;
	}
}
