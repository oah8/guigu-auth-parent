package oah.project.system.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisPlusConfig
 * @Description TODO
 * @Author _oah
 * @Date 2023.08.29 22:42
 * @Version 1.0
 */
/**
 * MybatisPlus配置类
 *
 */
@EnableTransactionManagement
@Configuration
@MapperScan("oah.project.system.mapper")
public class MybatisPlusConfig {

    /**
     * 插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor addPaginationInnerInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //向Mybatis过滤器链中添加分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}