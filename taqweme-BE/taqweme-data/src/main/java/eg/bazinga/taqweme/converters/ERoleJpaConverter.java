package eg.bazinga.taqweme.converters;

import eg.bazinga.taqweme.enums.ERole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ERoleJpaConverter implements AttributeConverter<ERole, String> {

    @Override
    public String convertToDatabaseColumn(ERole role) {
        return role == null ? null : role.getRole();
    }

    @Override
    public ERole convertToEntityAttribute(String role) {
        if (role == null) {
            return null;
        }

        return Stream.of(ERole.values())
                .filter(eRole -> eRole.getRole().equals(role))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
