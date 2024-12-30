package com.restful.adoptions;

import com.restful.adoptions.user.model.PermissionEntity;
import com.restful.adoptions.user.model.RoleEntity;
import com.restful.adoptions.user.model.RoleEnum;
import com.restful.adoptions.user.model.UserEntity;
import com.restful.adoptions.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AdoptionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptionsApplication.class, args);
	}

	public static String encodePassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	@Bean
	public CommandLineRunner init(UserRepository user) {

		return args -> {

			//* Create PERMISSIONS *//
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission= PermissionEntity.builder()
					.name("DELETE")
					.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("DEV")
					.build();



			//* Create ROLES *//
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionEntityList(Set.of(
							createPermission,
							readPermission,
							updatePermission,
							deletePermission
					))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionEntityList(Set.of(
							createPermission,
							updatePermission,
							readPermission
					))
					.build();

			RoleEntity roleRefuge = RoleEntity.builder()
					.roleEnum(RoleEnum.REFUGE)
					.permissionEntityList(Set.of(
							createPermission,
							updatePermission,
							readPermission
					))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEV)
					.permissionEntityList(Set.of(
							createPermission,
							readPermission,
							updatePermission,
							deletePermission,
							refactorPermission
					))
					.build();


			//* CREATE USERS *//
			UserEntity userDamian = UserEntity.builder()
					.password(encodePassword("1234"))
					.email("dam788@gmail.com")
					.username("dam788")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roleEntities(Set.of(roleAdmin))
					.build();

			UserEntity userAle = UserEntity.builder()
					.password(encodePassword("1234"))
					.email("alegnr@gmail.com")
					.username("ale_gnr")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roleEntities(Set.of(roleDeveloper))
					.build();

			UserEntity userRefugio = UserEntity.builder()
					.password(encodePassword("1234"))
					.email("refugio22@gmail.com")
					.username("refugio_23")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roleEntities(Set.of(roleRefuge))
					.build();

			UserEntity userCarlos = UserEntity.builder()
					.password(encodePassword("123456"))
					.username("carlos_cabj_97")
					.email("carlos_cabj_97@gmail.com")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roleEntities(Set.of(roleUser))
					.build();

			user.saveAll(List.of(
					userDamian,
					userAle,
					userRefugio,
					userCarlos
			));
		};
	}

}
