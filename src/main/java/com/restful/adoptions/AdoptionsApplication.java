package com.restful.adoptions;

import com.restful.adoptions.user.model.Permission;
import com.restful.adoptions.user.model.Role;
import com.restful.adoptions.user.model.RoleEnum;
import com.restful.adoptions.user.model.UserEntity;
import com.restful.adoptions.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AdoptionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptionsApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(UserRepository user) {

		return args -> {

			//* Create PERMISSIONS *//
			Permission createPermission = Permission.builder()
					.name("CREATE")
					.build();

			Permission readPermission = Permission.builder()
					.name("READ")
					.build();

			Permission updatePermission = Permission.builder()
					.name("UPDATE")
					.build();

			Permission deletePermission = Permission.builder()
					.name("DELETE")
					.build();

			Permission refactorPermission = Permission.builder()
					.name("DEV")
					.build();



			//* Create ROLES *//
			Role roleAdmin = Role.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(
							createPermission,
							readPermission,
							updatePermission,
							deletePermission
					))
					.build();

			Role roleUser = Role.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(
							createPermission,
							updatePermission,
							readPermission
					))
					.build();

			Role roleRefuge = Role.builder()
					.roleEnum(RoleEnum.REFUGE)
					.permissionList(Set.of(
							createPermission,
							updatePermission,
							readPermission
					))
					.build();

			Role roleDeveloper = Role.builder()
					.roleEnum(RoleEnum.DEV)
					.permissionList(Set.of(
							createPermission,
							readPermission,
							updatePermission,
							deletePermission,
							refactorPermission
					))
					.build();


			//* CREATE USERS *//
			UserEntity userEntityDamian = UserEntity.builder()
					.password("1234")
					.username("dam788")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userEntityAle = UserEntity.builder()
					.password("1234")
					.username("ale_gnr")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			UserEntity userEntityRefugio = UserEntity.builder()
					.password("1234")
					.username("refugio_23")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleRefuge))
					.build();

			UserEntity userEntityCarlos = UserEntity.builder()
					.password("1234")
					.username("carlos_cabj_97")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			user.saveAll(List.of(

					userEntityDamian,
					userEntityAle,
					userEntityRefugio,
					userEntityCarlos

			));
		};
	}

}
