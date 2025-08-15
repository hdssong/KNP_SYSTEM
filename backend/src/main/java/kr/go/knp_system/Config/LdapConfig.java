package kr.go.knp_system.config;

public class LdapConfig {
    
    // @Bean
    // public LdapContextSource contextSource() {
    //     LdapContextSource source = new LdapContextSource();
    //     source.setUrl("ldap://localhost:8390");
    //     source.setBase("dc=springframework,dc=org");

    //     return source;
    // }

    // @Bean
    // public AuthenticationManager authenticationManager(BaseLdapPathContextSource contextSource) {
    //     BindAuthenticator authenticator = new BindAuthenticator(contextSource);
    //     authenticator.setUserDnPatterns(new String[] { "uid={0},ou=people" });  // 로그인할 때 입력한 ID를 {0}자리에 넣어서 DN을 찾겠다
    //     return new ProviderManager(new LdapAuthenticationProvider(authenticator));
    // }

}
