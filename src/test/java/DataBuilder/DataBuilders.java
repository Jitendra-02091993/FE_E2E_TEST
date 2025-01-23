package DataBuilder;

import net.datafaker.Faker;

public class DataBuilders {
	private static final Faker FAKER = new Faker();
	
	public static UserDetails user_Details() {
		return UserDetails.builder()
				.firstName(FAKER.name().firstName())
				.middleName(FAKER.name().lastName())
				.lastName(FAKER.name().lastName())
				.emailID(FAKER.internet().emailAddress()).build();
				
	}

}
