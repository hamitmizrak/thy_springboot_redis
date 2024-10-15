package com.hamitmizrak.thy_springboot_redis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration: Bean tanımlarımını ve uygulamada yapılandırmalarını sağlamak içindir
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // CORS (Cross Origin Resource Sharing)
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry
                .addMapping("/**") //Tüm pointeler için CORS geçerli olsun
                //.allowedOrigins("*") // Bütün Kaynaklara izin ver
                .allowedOrigins("http://localhost:3000") // Sadece Belirli Kaynaklara izin ver
                .allowedMethods("GET", "POST", "PUT", "DELETE") // İzin verilen Http Metotları
                .allowedHeaders("*") //  izin verilen başlılar => Tüm başlıkları kabul et,
                .allowCredentials(true); // Kimlik doğrulama bilgilerinde izin ver(Cookie, header, authorization vs)
    }

    // Statik Kaynaklara İzin vermem (Spring MVC içindir)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry
                .addResourceHandler("/resources/**")// Statik kod kaynakların URL deseni
                .addResourceLocations("/public/", "classpath:/static/") //Kaynakların Yeri
                .setCachePeriod(3600); // Cache Süresi Saniye cinsinden: 1=60 saniye 60*60=3600 =1 saat boyunca statik kod kaynaklarını cache belleğinde sakla
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Interceptor
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

    }


} //end WebConfig
