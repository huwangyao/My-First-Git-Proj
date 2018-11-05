package cn.flydiy.web.config;

import cn.flydiy.common.annotation.KeepName;
import cn.flydiy.core.annotation.WebConfiguration;
import cn.flydiy.core.constant.Constant;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@WebConfiguration
public class SpringConfig {

    /**
     * 资源验证
     *
     * @return
     */
    @Bean
    @KeepName
    public FilterRegistrationBean ResourceFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        ResourceFilter filter = new ResourceFilter();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns(Constant.REQUEST_PATH + "/*");
        filterRegistrationBean.setOrder(15);

        return filterRegistrationBean;
    }
}
