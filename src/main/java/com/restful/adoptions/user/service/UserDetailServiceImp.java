package com.restful.adoptions.user.service;

import com.restful.adoptions.user.controller.dto.AuthLoginRequest;
import com.restful.adoptions.user.controller.dto.AuthReponse;
import com.restful.adoptions.user.controller.dto.UserDTO;
import com.restful.adoptions.user.model.UserEntity;
import com.restful.adoptions.user.repository.UserRepository;
import com.restful.adoptions.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoleEntities()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoleEntities().stream()
                .flatMap(role -> role.getPermissionEntityList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList
        );
    }

    public AuthReponse loginUser(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthReponse authReponse = new AuthReponse(username, "User loged successfuly", accessToken, true);
        return authReponse;
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }
        return  new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public List <UserEntity> getAllUsers () {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById (Long id ) {
        return userRepository.findById( id );
    }

    public UserEntity createOneUser (UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity updateOneUser (UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity deleteOneUser (UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserDTO convertToUserDTO(UserEntity user) {
        return new UserDTO(
                user.getIdUser(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled(),
                user.getRoleEntities()
        );
    }
}
