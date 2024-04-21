package e4i.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import e4i.security.*;
import e4i.security.jwt.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    private final CorsFilter corsFilter;
    private final SecurityProblemSupport problemSupport;

    public SecurityConfiguration(TokenProvider tokenProvider, CorsFilter corsFilter, SecurityProblemSupport problemSupport) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.problemSupport = problemSupport;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/app/**/*.{js,html}")
            .antMatchers("/i18n/**")
            .antMatchers("/content/**")
            .antMatchers("/swagger-ui/index.html")
            .antMatchers("/test/**")
        	.antMatchers("/api/portal-user-organizations/search/{name}/{language}");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
            .disable()
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
        .and()
            .headers()
            .contentSecurityPolicy("default-src 'self' localhost:* ws://localhost:*; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com http://localhost; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
            //.contentSecurityPolicy("default-src *; connect-src *")
            .and()
            .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
        .and()
            .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
        .and()
            .frameOptions()
            .deny()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/api/upload").permitAll()
            .antMatchers("/api/companies/*").permitAll()
            .antMatchers("/api/researchers/*").permitAll()
            .antMatchers("/api/institutions/*").permitAll()
            .antMatchers("/api/copyripuo/*").permitAll() 
            .antMatchers("/api/companies/**").permitAll()
            .antMatchers("/api/researchers/**").permitAll()
            .antMatchers("/api/institutions/**").permitAll()
            .antMatchers("/api/copyripuo/**").permitAll() 
            .antMatchers("/api/upload/*").permitAll()
            .antMatchers("/api/files/**").permitAll()
            .antMatchers("/api/files/*").permitAll()
            .antMatchers("/api/load/*").permitAll()
            .antMatchers("/api/research-infrastructures/image/*").permitAll()            
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/activate").permitAll()
            .antMatchers("/api/account/reset-password/init").permitAll()
            .antMatchers("/api/account/reset-password/finish").permitAll()
            .antMatchers("/api/puo-legal-statuses").permitAll()
            .antMatchers("/api/portal-user-statuss").permitAll()
            .antMatchers("/api/research-infrastructures-pregled/**").permitAll()
            .antMatchers("/api/ri-services-pregled/**").permitAll()
            .antMatchers("/api/ri-services-homepage/search").permitAll()            
            .antMatchers("/api/scientific-domains/search").permitAll()
            .antMatchers("/api/portal-user-organizations-pregled/**").permitAll()
            .antMatchers("/api/portal-user-organizations-institutions/*").permitAll()
            .antMatchers("/api/portal-user-organizations-institutions/**").permitAll()
            .antMatchers("/api/portal-user-organizations-from-api/*").permitAll()
            .antMatchers("/api/research-institutions/**").permitAll()
            .antMatchers("/api/research-institutions-autocomplete/*").permitAll()
            .antMatchers("/api/research-institutions-ordered/*").permitAll() 
            .antMatchers("/api/researchers-ordered/*").permitAll() 
            .antMatchers("/api/researchers-ordered/**").permitAll() 
            .antMatchers("/api/portal-users-pregled/**").permitAll()
            .antMatchers("/api/b2b/*").permitAll()
            .antMatchers("/api/b2b/**").permitAll()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/info").permitAll()
            .antMatchers("/management/prometheus").permitAll()
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
        .and()
            .httpBasic()
        .and()
            .apply(securityConfigurerAdapter());
        // @formatter:on
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
