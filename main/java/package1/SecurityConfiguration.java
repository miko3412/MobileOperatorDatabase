package package1;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
				.withUser("user")
				.password("user")
				.roles("USER")
				.and()
				.withUser("admin")
				.password("admin")
				.roles("ADMIN")
				.and()
				.withUser("manager")
				.password("123")
				.roles("MANAGER");
	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/poczty/").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/poczty").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/biura/**").hasRole("ADMIN")
			.antMatchers("/poczty/new/**").hasRole("ADMIN")
			.antMatchers("/poczty/edit/**").hasRole("ADMIN")
			.antMatchers("/poczty/delete/**").hasRole("ADMIN")
			.antMatchers("/adresy/new/**").hasRole("ADMIN")
			.antMatchers("/adresy/edit/**").hasRole("ADMIN")
			.antMatchers("/adresy/delete/**").hasRole("ADMIN")
			.antMatchers("/adresy/").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/adresy").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/pracownicy/new/**").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/pracownicy/edit/**").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/pracownicy/delete/**").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/wynagrodzenia/new/**").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/wynagrodzenia/edit/**").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/wynagrodzenia/delete/**").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/").permitAll()
			.antMatchers("/pracownicy").hasAnyRole("ADMIN","USER","MANAGER")
			.antMatchers("/wynagrodzenia").hasAnyRole("ADMIN","USER","MANAGER")
			.and().exceptionHandling().accessDeniedPage("/brak_dostepu")
			.and().logout().logoutSuccessUrl("/")
			.and().formLogin();
		
	}
	 
	
}
