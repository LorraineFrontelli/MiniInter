package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacaoRegex {
    public static boolean verificarEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w.-]+@([\\w-]+\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean verificarSenha(String senha) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9!@#$%^&*()_\\-+=<>?{}\\[\\]~]{8,}$");
        return pattern.matcher(senha).matches();
    }
}

