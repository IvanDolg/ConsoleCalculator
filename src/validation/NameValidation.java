package validation;

import interfaces.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidation implements Validation {
    @Override
    public boolean validate(String type) {
        Pattern compile = Pattern.compile("^[a-zA-Z0-9._%+-]{4,}$");
        Matcher matcher = compile.matcher(type);
        return matcher.matches();
    }
}
