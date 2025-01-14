package com.restful.adoptions.user.service;

import com.restful.adoptions.user.controller.dto.AuthCreateUserRequest;
import com.restful.adoptions.user.controller.dto.AuthLoginRequest;
import com.restful.adoptions.user.controller.dto.AuthReponse;
import com.restful.adoptions.user.controller.dto.UserDTO;
import com.restful.adoptions.user.model.RoleEntity;
import com.restful.adoptions.user.model.UserEntity;
import com.restful.adoptions.user.repository.RoleRepository;
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

import java.util.*;


@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


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

        return new AuthReponse(username, "User loged successfuly", accessToken, true);
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

    public AuthReponse createUser(AuthCreateUserRequest authCreateUserRequest) throws IllegalAccessException {
        String username = authCreateUserRequest.username();
        String email = authCreateUserRequest.email();
        String password = authCreateUserRequest.password();
        List<String> roleRequest = authCreateUserRequest.roleRequest().roleListName();

        Set<RoleEntity> roleEntitySet = new HashSet<>(roleRepository.findRoleEntitiesByRoleEnumIn(roleRequest));

        if(roleEntitySet.isEmpty()) {
            throw  new IllegalAccessException("The roles specified does not exist.");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .roleEntities(roleEntitySet)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .isEnabled(true)
                .build();

        UserEntity userCreated = userRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoleEntities().forEach(
                role -> authorityList.add(
                        new SimpleGrantedAuthority( "ROLE_".concat( role.getRoleEnum().name() ))
                )
        );
        userCreated.getRoleEntities()
                .stream()
                .flatMap( role -> role.getPermissionEntityList().stream())
                .forEach( permission -> authorityList.add(new SimpleGrantedAuthority( permission.getName() )));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userCreated.getUsername(),
                userCreated.getPassword(),
                authorityList
        );

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthReponse(
                userCreated.getUsername(),
                "User created Successfully",
                accessToken,
                true
        );
    }

    public List <UserEntity> getAllUsers () {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById (Long id ) {
        return userRepository.findById( id );
    }

    public UserDTO convertToUserDTO(UserEntity user) {
        return new UserDTO(
                user.getIdUser(),
                user.getUsername(),
                user.getEmail(),
                user.getRoleEntities()
        );
    }
}
