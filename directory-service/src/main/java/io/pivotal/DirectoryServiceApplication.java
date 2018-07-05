package io.pivotal;

import io.pivotal.domain.Person;
import io.pivotal.repository.DirectoryRepository;
import io.pivotal.domain.Directory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class DirectoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner addPersons(DirectoryRepository repo){
		return args -> {
			List<Person> people = asList(
					new Person("John","Doe","john@email.com","Denver, CO","281-802-0425"),
					new Person("Herman", "Merman", "hermanthemerman@gmail.com", "Great Barrier Reef", "713-316-6565"),
					new Person("Gary", "Gobbles", "gg@nore.com", "Gary, IN", "832-666-9090")

			);
			repo.save(new Directory("my-directory", people));
		};
	}

}
