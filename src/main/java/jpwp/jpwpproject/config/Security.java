package jpwp.jpwpproject.config;

import jpwp.jpwpproject.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    private final UserService UserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Security(jpwp.jpwpproject.service.UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        UserService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/registration/**")
                .permitAll()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/console/**")
                .permitAll()
                .antMatchers("/dictionary/**")
                .permitAll()
                .antMatchers("/user/**")
                .permitAll()
                .antMatchers("/addWord/**")
                .permitAll()
                .anyRequest()
                .authenticated();/*.and()
                .formLogin();*/
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.inMemoryAuthentication()
                .withUser("user").password(bCryptPasswordEncoder.encode("user")).roles("USER");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(UserService);
        return provider;
    }
}
