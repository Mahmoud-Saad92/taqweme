package eg.bazinga.taqweme.enhancers;

import eg.bazinga.taqweme.domains.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomTokenEnhancer implements TokenEnhancer {

    public CustomTokenEnhancer() {
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();

        if (user != null) {
            additionalInfo.put("user_id", user.getId());
            additionalInfo.put("user_name", user.getUsername());
            additionalInfo.put("authorities", user.getAuthorities().stream().map(Objects::toString).collect(Collectors.joining(",")));
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }

}
