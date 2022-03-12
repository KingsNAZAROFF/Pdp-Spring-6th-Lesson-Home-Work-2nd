package uz.pdp.pdpspring6thlessonhomework2nd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring6thlessonhomework2nd.entitiy.User;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.ApiResponse;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.RegisterDto;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.LavozimRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.UserRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.utils.AppConstants;


@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword())){
            return new ApiResponse("Parollar mos emas",false);
        }

        boolean byUsername = userRepository.existsByUsername(registerDto.getUsername());
        if (byUsername){
            return new ApiResponse("Bunday username avval ro'yxatdan o'tgan !",false);
        }
        User user = new User();
        user.setFullName(registerDto.getFullName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setLavozim(lavozimRepository.findByName(AppConstants.USER).orElseThrow());
        user.setEnabled(true);
        userRepository.save(user);

        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz ",true);
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = userRepository.findByEmail(username);
//        if (optionalUser.isPresent()){
//            return optionalUser.get();
//        }
//        throw  new UsernameNotFoundException(username +" topilmadi");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + "user topilmadi ! ! !"));
    }
}
