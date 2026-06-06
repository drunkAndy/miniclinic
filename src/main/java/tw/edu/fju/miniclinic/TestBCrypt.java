package tw.edu.fju.miniclinic;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class TestBCrypt {

    public static void main(String[] args) {

        System.out.println(
                BCrypt.hashpw(
                        "pass1234",
                        BCrypt.gensalt()
                )
        );

    }
}
