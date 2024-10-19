package com.hamitmizrak.thy_springboot_redis.config;

import com.hamitmizrak.thy_springboot_redis.bean.PasswordEncoderBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

// DİKKATTTTTT: username,password çalışmazsa
// 1.YOL : Öncelikle Browser cache belleği silmelisiniz
// 2.YOL : Eğer Redis varsa terminalde şu komutları yaz
// Docker Desktop => Container => Terminal
// $ redis-cli FLUSHDB
// $ redis-cli FLUSHALL

// LOMBOK
@RequiredArgsConstructor

// SECURITY
@Configuration //Bean
@EnableWebSecurity //Web Security kullanmak
public class SecurityConfig {

    // Injection
    private final PasswordEncoderBean passwordEncoderBean;

    // ADMIN
    // username: hamitmizrak@gmail.com
    // password: root

    // WRITER
    // username: hamitmizrak
    // password: root

    // Email
    // import org.springframework.beans.factory.annotation.Value
    @Value("${spring.security.superadmin.email}")
    private String superAdminEmail;

    @Value("${spring.security.superadmin.password}")
    private String superAdminPassword;

    @Value("${spring.security.superadmin.roles}")
    private String superAdminRoles;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Static WEB Dosyalarına erişimi (HTML,CSS,JS)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web)-> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                .requestMatchers(new AntPathRequestMatcher("/favicon.ico"))
                .requestMatchers(new AntPathRequestMatcher("/html/**"))
                .requestMatchers(new AntPathRequestMatcher("/css/**"))
                .requestMatchers(new AntPathRequestMatcher("/js/**"))
                .requestMatchers(new AntPathRequestMatcher("/img/**"))
                .requestMatchers(new AntPathRequestMatcher("/lib/**"));
    } //end webSecurityCustomizer

    // Database üzerinden olmadan In-Memory olarak Role Management
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        // ADMIN ROLES
        UserDetails admin= User
                .withDefaultPasswordEncoder()
                .username(superAdminEmail)
                .password(superAdminPassword)
                .roles(superAdminRoles)
                .build();

        UserDetails writer= User
                .withDefaultPasswordEncoder()
                .username("hamitmizrak")
                .password("root")
                .roles("ROLES_WRITER")
                .build();

        return new InMemoryUserDetailsManager(admin, writer);
    } //end InMemoryUserDetailsManager


    // http://localhost:4444/
    // http://localhost:4444/h2-console/
    // http://localhost:4444/swagger-ui/index.html
    // http://localhost:4444/api/address/find/1

    // İzin verilmesi gereken Path
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {

        // CSRF
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // Web Page
        httpSecurity.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers(new MvcRequestMatcher(introspector, "/")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector, "/index")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector, "/index.html")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector, "/index.htm")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector, "/login")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector, "/logout")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector, "/admin")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector, "/swagger-ui/**")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector, "/h2-console/**")).permitAll()
                        //.requestMatchers(new MvcRequestMatcher(introspector, "/api/**")).permitAll()
                        .anyRequest().authenticated())

         // POSTMAN kullanabilmek için (Http Aç)
        //.httpBasic(Customizer.withDefaults())

        // Page Form İzin ver
        .formLogin(Customizer.withDefaults())

                // Logout
       .logout().logoutUrl("/logout").invalidateHttpSession(true);

        return httpSecurity.build();
    }

} // end SecurityFilterChain


// 1.ADIM
// Spring Security: Şimdilik dahil etme, çünkü Bcrypted kullancağım ancak Spring security için gerekli kütüphaneleri dahil
// Buradaki exclude ne zaman kapatmam gerekiyor ? cevap: Spring Security ile çalıştığımız zaman kapat
/*@SpringBootApplication(exclude = {
        //SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
)*/

// Spring Security kullandığım zaman => @SpringBootApplication


// 2.ADIM
/*devtools: otomatik sistemi compiler etmek içindir-->
        <!--devtools: ancak Spring security kapatabilirsin-->
        <!-- <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
        <scope>runtime</scope>
        </dependency>
  */


// 3.ADIM (Default =>Login )
// Username: user
// Password: 9be827ae-4d3b-4b0f-b37a-a4666e1ce59f

// 4.ADIM (application.properties)
/*
### Spring Security  #################################################################
spring.command.line.rules.superadmin.nickname=superadmin
spring.command.line.rules.superadmin.name=Hamit
spring.command.line.rules.superadmin.surname=Mızrak
spring.command.line.rules.superadmin.email=hamitmizrak@gmail.com
spring.command.line.rules.superadmin.password=root
spring.command.line.rules.superadmin.roles=SUPER_ADMIN
*/
