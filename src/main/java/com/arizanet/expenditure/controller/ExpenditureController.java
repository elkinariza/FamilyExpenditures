package com.arizanet.expenditure.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arizanet.expenditure.data.Expenditure;
import com.arizanet.expenditure.data.ExpenditureType;
import com.arizanet.expenditure.exception.ExpenditureNotFoundException;
import com.arizanet.expenditure.repository.ExpenditureRepository;
import com.arizanet.expenditure.repository.ExpenditureTypeRepository;

@RestController
public class ExpenditureController {

	public static final Logger log = LoggerFactory.getLogger(ExpenditureController.class);

	private ExpenditureRepository expenditureRepository;
	private ExpenditureTypeRepository expenditureTypeRepository;
	private ExpenditureModelAssembler assembler;

	public ExpenditureController(ExpenditureRepository expenditureRepository,
			ExpenditureTypeRepository expenditureTypeRepository, ExpenditureModelAssembler assembler) {
		super();
		this.expenditureRepository = expenditureRepository;
		this.expenditureTypeRepository = expenditureTypeRepository;
		this.assembler = assembler;
	}

	@GetMapping("/expenditures")
	public CollectionModel<EntityModel<Expenditure>> all() {
		
		List<Expenditure> listExp = expenditureRepository.findAll();
		
		log.debug("expenditures:" + listExp);
		
		Stream<Expenditure> streamExp = listExp.stream();
		
		log.debug("streamExp: " + streamExp);
		
		Stream<EntityModel<Expenditure>> stream2Exp = streamExp.map(assembler::toModel);
		
		List<EntityModel<Expenditure>> expenditures = stream2Exp.collect(Collectors.toList());

		return CollectionModel.of(expenditures, linkTo(methodOn(ExpenditureController.class).all()).withSelfRel());

	}

	@PostMapping("/expenditures")
	public ResponseEntity<?> newExpenditure(@RequestBody Expenditure expenditure) {

		log.debug("expenditure: " + expenditure);

		String type = expenditure.getExpenditureType() != null && expenditure.getExpenditureType().getType() != null
				? expenditure.getExpenditureType().getType()
				: "Ninguna";

		List<ExpenditureType> types = expenditureTypeRepository.findByType(type);

		log.debug("ExpenditureType list: " + types);
		ExpenditureType expenditureType = new ExpenditureType(type);
		if (types.isEmpty()) {
			expenditureType = expenditureTypeRepository.save(expenditureType);
		} else {
			expenditureType = types.get(0);
		}

		expenditure.setExpenditureType(expenditureType);

		EntityModel<Expenditure> entityModel = assembler.toModel(expenditureRepository.save(expenditure));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);

	}

	@GetMapping("/expenditures/{expenditureId}")
	public EntityModel<Expenditure> one(@PathVariable Long expenditureId) {

		Expenditure expenditure = expenditureRepository.findById(expenditureId)
				.orElseThrow(() -> new ExpenditureNotFoundException(expenditureId));

		return assembler.toModel(expenditure);
	}

	@PutMapping("/expenditures/{expenditureId}")
	ResponseEntity<?> replaceExpenditure(@RequestBody Expenditure newExpenditure, @PathVariable Long expenditureId) {

		String type = newExpenditure.getExpenditureType() != null
				&& newExpenditure.getExpenditureType().getType() != null ? newExpenditure.getExpenditureType().getType()
						: "Ninguna";

		List<ExpenditureType> types = expenditureTypeRepository.findByType(type);

		log.debug("ExpenditureType list: " + types);
		ExpenditureType expenditureType = new ExpenditureType(type);
		if (types.isEmpty()) {
			expenditureType = expenditureTypeRepository.save(expenditureType);
		} else {
			expenditureType = types.get(0);
		}

		newExpenditure.setExpenditureType(expenditureType);

		Expenditure updatedExpenditure = expenditureRepository.findById(expenditureId).map(expenditure -> {
			expenditure.setAccount(newExpenditure.getAccount());
			expenditure.setDescription(newExpenditure.getDescription());
			expenditure.setExpenseDate(newExpenditure.getExpenseDate());
			expenditure.setMonthlyExpense(newExpenditure.getMonthlyExpense());
			expenditure.setValue(newExpenditure.getValue());
			expenditure.setExpenditureType(newExpenditure.getExpenditureType());
			return expenditureRepository.save(expenditure);
		}).orElseGet(() -> {
			newExpenditure.setExpenditureId(expenditureId);
			return expenditureRepository.save(newExpenditure);
		});

		EntityModel<Expenditure> entityModel = assembler.toModel(updatedExpenditure);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping("/expenditures/{expenditureId}")
	ResponseEntity<?> deleteExpenditure(@PathVariable Long expenditureId) {

		expenditureRepository.deleteById(expenditureId);

		return ResponseEntity.noContent().build();
	}

}
