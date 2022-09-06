package com.arizanet.expenditure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FamilyExpenditureApplication {
	
	//private static final Logger log = LoggerFactory.getLogger(FamilyExpenditureApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FamilyExpenditureApplication.class, args);
	}
	
/*
	@Bean
	CommandLineRunner initDatabase(ExpenditureTypeRepository expenditureTypeRepository) {

		CommandLineRunner runner = args -> {
			expenditureTypeRepository.save(new ExpenditureType("Alimentación"));
			expenditureTypeRepository.save(new ExpenditureType("Aseo"));
			expenditureTypeRepository.save(new ExpenditureType("Cuidado Santiago"));
			expenditureTypeRepository.save(new ExpenditureType("Donaciones"));
			expenditureTypeRepository.save(new ExpenditureType("Entretenimiento"));
			expenditureTypeRepository.save(new ExpenditureType("Gastos Carro"));
			expenditureTypeRepository.save(new ExpenditureType("Gasolina"));
			expenditureTypeRepository.save(new ExpenditureType("Gastos Médicos"));
			expenditureTypeRepository.save(new ExpenditureType("Golosinas"));
			expenditureTypeRepository.save(new ExpenditureType("Imprevistos"));
			expenditureTypeRepository.save(new ExpenditureType("Colegio Santiago"));
			expenditureTypeRepository.save(new ExpenditureType("Lavandería"));
			expenditureTypeRepository.save(new ExpenditureType("Mercado"));
			expenditureTypeRepository.save(new ExpenditureType("Obligaciones Bancarias"));
			expenditureTypeRepository.save(new ExpenditureType("Parqueadero"));
			expenditureTypeRepository.save(new ExpenditureType("Prestamos"));
			expenditureTypeRepository.save(new ExpenditureType("Seguro Exequial"));
			expenditureTypeRepository.save(new ExpenditureType("Servicios"));
			expenditureTypeRepository.save(new ExpenditureType("Transferencia"));
			expenditureTypeRepository.save(new ExpenditureType("Transporte"));

			expenditureTypeRepository.findAll().forEach(expeditureType -> log.info("ExpeditureType preloaded " + expeditureType));

		};
		
		return runner;
	}
*/
}
