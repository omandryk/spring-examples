package com.icoderman.app.controller;

import com.icoderman.app.exceptions.PersonNotFoundException;
import com.icoderman.app.model.HttpError;
import com.icoderman.app.model.Person;
import com.icoderman.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private PersonService personService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Person> allPersons() {
		return personService.getAll();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void savePerson(@RequestBody Person person) {
		System.out.println(person);
	}

	@RequestMapping(value = "/v1", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Person> savePerson_V1(@RequestBody Person person) {
		Person personResult = personService.save(person);
		HttpHeaders headers = new HttpHeaders();
		// hardcoded url is bad idea
		URI locationUri = URI.create("http://localhost:8080/person/" + personResult.getId());
		headers.setLocation(locationUri);
		return new ResponseEntity<>(person, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "v2", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Person> savePerson_V2(@RequestBody Person person, UriComponentsBuilder ucb) {
		Person personResult = personService.save(person);
		HttpHeaders headers = new HttpHeaders();
		URI locationUri = ucb.path("/person/").path(String.valueOf(person.getId())).build().toUri();
		headers.setLocation(locationUri);
		return new ResponseEntity<>(personResult, headers, HttpStatus.CREATED);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void updatePerson(@RequestBody Person person) {
		System.out.println(person);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletePerson(@PathVariable Long id) {
		System.out.println(id);
	}

	// Error handling
	//--------------------

	// what if person is not found?
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Person getPersonById_V1(@PathVariable Long id) {
		Person person = new Person();

		return person;
	}

	// if person is null ResponseEntity with null is not good
	@RequestMapping(value = "/v2/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> getPersonById_V2(@PathVariable Long id) {
		Person person = personService.findOne(id);
		HttpStatus status = person != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Person>(person, status);
	}

	// too much logic that will be duplicated for many similar methods
	@RequestMapping(value = "/v3/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPersonById_V3(@PathVariable Long id) {
		Person person = personService.findOne(id);
		if (person == null) {
			HttpError error = new HttpError(4, "Person [" + id + "] not found");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	// variant 4
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<HttpError> personNotFound(PersonNotFoundException e) {
		Long personId = e.getPersonId();
		HttpError error = new HttpError(4, "Person [" + personId + "] not found");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/v4/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> getPersonById_V4(@PathVariable Long id) {
		Person person = personService.findOne(id);
		if (person == null) {
			throw new PersonNotFoundException(id);
		}
		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	// variant 4.1
	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public HttpError personNotFound_V41(PersonNotFoundException e) {
		Long personId = e.getPersonId();
		return new HttpError(4, "Person [" + personId + "] not found");
	}

	@RequestMapping(value = "/v41/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Person getPersonById_V41(@PathVariable Long id) {
		Person person = personService.findOne(id);
		if (person == null) {
			throw new PersonNotFoundException(id);
		}
		return person;
	}

}
