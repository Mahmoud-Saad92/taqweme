package eg.bazinga.taqweme.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eg.bazinga.taqweme.serializers.CustomOauthExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

    public CustomOauthException(String msg) {
        super(msg);
    }
}
