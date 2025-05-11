package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "lokesh"
            ,"Lucky"
    })
    public void findByUserNameTest(String name){
        assertNotNull(userRepository.findByUserName(name));
    }
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "lokesh"
            ,"Lucky"
    })
    public void findByUserNameTest2(String name){
        assertNotNull(userRepository.findByUserName(name));
    }
}
