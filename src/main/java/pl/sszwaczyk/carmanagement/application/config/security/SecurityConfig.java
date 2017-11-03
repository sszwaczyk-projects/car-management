package pl.sszwaczyk.carmanagement.application.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
//
//    @Autowired
//    private LogoutSuccessHandler myLogoutSuccessHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler authenticationFailureHandler;
//
//    @Autowired
//    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

//        http.csrf().disable().authorizeRequests().anyRequest().permitAll();

        http.csrf().disable()
                .authorizeRequests().antMatchers("/registration/**").anonymous()
                .antMatchers("/dashboard").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home.html")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/login*", "/login*", "/logout*", "/signin/**", "/signup/**", "/customLogin",
//                        "/user/registration*", "/registrationConfirm*", "/expiredAccount*", "/registration*",
//                        "/badUser*", "/user/resendRegistrationToken*", "/forgetPassword*", "/user/resetPassword*",
//                        "/user/changePassword*", "/emailError*", "/resources/**", "/old/user/registration*", "/successRegister*", "/qrcode*").permitAll()
//                .antMatchers("/invalidSession*").anonymous()
//                .antMatchers("/user/updatePassword*", "/user/savePassword*", "/updatePassword*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
//                .anyRequest().hasAuthority("READ_PRIVILEGE")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/homepage.html")
//                .failureUrl("/login?error=true")
//                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(authenticationFailureHandler)
//                .authenticationDetailsSource(authenticationDetailsSource)
//                .permitAll()
//                .and()
//                .sessionManagement()
//                .invalidSessionUrl("/invalidSession.html")
//                .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
//                .sessionFixation().none()
//                .and()
//                .logout()
//                .logoutSuccessHandler(myLogoutSuccessHandler)
//                .invalidateHttpSession(false)
//                .logoutSuccessUrl("/logout.html?logSucc=true")
//                .deleteCookies("JSESSIONID")
//                .permitAll();

    }

//    @Bean
//    public SessionRegistry sessionRegistry() {
//        return new SessionRegistryImpl();
//    }
}
