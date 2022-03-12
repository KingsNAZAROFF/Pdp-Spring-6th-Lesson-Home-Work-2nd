package uz.pdp.pdpspring6thlessonhomework2nd.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.pdpspring6thlessonhomework2nd.entitiy.Lavozim;
import uz.pdp.pdpspring6thlessonhomework2nd.entitiy.User;
import uz.pdp.pdpspring6thlessonhomework2nd.entitiy.enums.Permissions;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.LavozimRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.UserRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.utils.AppConstants;
import static uz.pdp.pdpspring6thlessonhomework2nd.entitiy.enums.Permissions.*;


import java.util.Arrays;


@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Value("${spring.datasource.initialization-mode}")
    private String initialModeType;

    @Override
    public void run(String... args) throws Exception {
       if (initialModeType.equals("always")){
           Permissions[] values = Permissions.values();
           Lavozim admin = lavozimRepository.save(new Lavozim(
                   AppConstants.ADMIN,
                   Arrays.asList(values),
                   "Kompaniya egasi"
           ));
           Lavozim user = lavozimRepository.save(new Lavozim(
                   AppConstants.USER,
                   Arrays.asList(CLIENT),
                   "Oddiy foydalanuvchi"
           ));
           userRepository.save(new User(
                   "Admin",
                   passwordEncoder.encode("admin"),
                   "admin123",
                   admin,
                   true

           ));
           userRepository.save(new User(
                   "User",
                   "user",
                   passwordEncoder.encode("user123"),
                   user,
                   true

           ));
       }

    }
}
