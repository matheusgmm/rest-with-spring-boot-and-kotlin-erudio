package br.com.erudio.controller

import br.com.erudio.data.vo.v1.PersonVO
import br.com.erudio.services.PersonService
import br.com.erudio.util.MediaType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.models.media.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
class PersonController {

    @Autowired
    private lateinit var service: PersonService;

    @GetMapping(produces =
    [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Finds all People", description = "Finds all People",
        tags = ["People"],
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema(schema = Schema(implementation = PersonVO::class)))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "No Content",
                responseCode = "204",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Bad Request",
                responseCode = "400",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Unauthorized",
                responseCode = "401",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Not Found",
                responseCode = "404",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Internal Error",
                responseCode = "500",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
        ]
    )
    private fun findALl(): List<PersonVO> {
        return service.findAll();
    }

    @CrossOrigin(origins = ["http://localhost:8080"])
    @GetMapping(value=["/{id}"], produces =
    [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Finds a Person", description = "Finds a Person",
        tags = ["People"],
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = PersonVO::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "No Content",
                responseCode = "204",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Bad Request",
                responseCode = "400",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Unauthorized",
                responseCode = "401",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Not Found",
                responseCode = "404",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Internal Error",
                responseCode = "500",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
        ]
    )
    fun findById(@PathVariable(value="id") id: Long ): PersonVO {
        return service.findById(id);
    }

    @CrossOrigin(origins = ["http://localhost:8080", "https://erudio.com.br"])
    @PostMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YAML],
                 produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Adds a new Person", description = "Adds a new Person",
        tags = ["People"],
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = PersonVO::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Bad Request",
                responseCode = "400",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Unauthorized",
                responseCode = "401",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Internal Error",
                responseCode = "500",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
        ]
    )
    fun create(@RequestBody person: PersonVO): PersonVO {
        return service.create(person);
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
                produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Updates a person's information", description = "Updates a person's information",
        tags = ["People"],
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = PersonVO::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "No Content",
                responseCode = "204",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Bad Request",
                responseCode = "400",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Unauthorized",
                responseCode = "401",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Not Found",
                responseCode = "404",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Internal Error",
                responseCode = "500",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
        ]
    )
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person);
    }

    @DeleteMapping(value = ["/{id}"], produces =
    [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Deletes a Person", description = "Deletes a Person",
        tags = ["People"],
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "No Content",
                responseCode = "204",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Bad Request",
                responseCode = "400",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Unauthorized",
                responseCode = "401",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Not Found",
                responseCode = "404",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                description =  "Internal Error",
                responseCode = "500",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]

            ),
        ]
    )
    fun delete(@PathVariable(value="id") id: Long): ResponseEntity<*> {
        service.delete(id);
        return ResponseEntity.noContent().build<Any>();
    }


}