package validation;

import interfaces.Validation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation implements Validation {
    @Override
    public boolean validate(String type) {
        Pattern compile = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$");
        Matcher matcher = compile.matcher(type);
        return matcher.matches();
    }
}
