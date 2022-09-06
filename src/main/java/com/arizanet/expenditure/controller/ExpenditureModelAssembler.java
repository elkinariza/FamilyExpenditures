package com.arizanet.expenditure.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.arizanet.expenditure.data.Expenditure;

@Component
public class ExpenditureModelAssembler implements RepresentationModelAssembler<Expenditure, EntityModel<Expenditure>> {

	public static final Logger log = LoggerFactory.getLogger(ExpenditureModelAssembler.class);

	@Override
	public EntityModel<Expenditure> toModel(Expenditure expenditure) {

		log.debug("Ingresando en método Assembler");
		log.debug("expenditure: " + expenditure);
		log.debug("expenditure.getExpenditureId(): " + expenditure.getExpenditureId());

		return EntityModel.of(expenditure, //
				linkTo(methodOn(ExpenditureController.class).one(expenditure.getExpenditureId())).withSelfRel(),
				linkTo(methodOn(ExpenditureController.class).all()).withRel("expenditures"));
	}

}
