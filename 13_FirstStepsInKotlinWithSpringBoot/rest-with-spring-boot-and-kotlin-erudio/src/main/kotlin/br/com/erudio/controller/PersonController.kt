package br.com.erudio.controller

import br.com.erudio.data.vo.v1.PersonVO
import br.com.erudio.services.PersonService
import br.com.erudio.util.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonService;

    @GetMapping(produces =
    [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun findALl(): List<PersonVO> {
        return service.findAll();
    }

    @GetMapping(value=["/{id}"], produces =
    [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun findById(@PathVariable(value="id") id: Long ): PersonVO {
        return service.findById(id);
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YAML],
                 produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun create(@RequestBody person: PersonVO): PersonVO {
        return service.create(person);
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
                produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person);
    }

    @DeleteMapping(value = ["/{id}"], produces =
    [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun delete(@PathVariable(value="id") id: Long): ResponseEntity<*> {
        service.delete(id);
        return ResponseEntity.noContent().build<Any>();
    }


}