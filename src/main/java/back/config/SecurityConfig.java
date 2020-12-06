package back.config;

import back.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //страницы без авторизации
        http.authorizeRequests().antMatchers("/", "/signup", "/login", "/logout").permitAll();
        //страницы авторизованных пользователей
        http.authorizeRequests().antMatchers().access("hasRole('" + Role.ROLE_USER + "')");
        //страницы администрирования
        http.authorizeRequests().antMatchers().access("hasRole('" + Role.ROLE_ADMIN + "')");
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

}
