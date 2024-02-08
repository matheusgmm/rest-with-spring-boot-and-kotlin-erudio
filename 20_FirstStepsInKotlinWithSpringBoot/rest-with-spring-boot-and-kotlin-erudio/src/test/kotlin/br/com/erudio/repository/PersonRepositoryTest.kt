package br.com.erudio.repository

import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest
import br.com.erudio.model.Person
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonRepositoryTest : AbstractIntegrationTest() {

    @Autowired
    private lateinit var repository: PersonRepository

    private lateinit var person: Person


    @BeforeAll
    fun setup() {
        person = Person()
    }


    @Test
    @Order(1)
    fun testFindByName() {
        val pageable: Pageable = PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "firstName"))
        person = repository.findPersonByName("abb", pageable).content[0]


        assertNotNull(person)
        assertNotNull(person.id)
        assertNotNull(person.firstName)
        assertNotNull(person.lastName)
        assertNotNull(person.address)
        assertNotNull(person.gender)

        assertEquals("Abbe", person.firstName)
        assertEquals("Quilleash", person.lastName)
        assertEquals("1 South Avenue", person.address)
        assertEquals("Female", person.gender)
        assertEquals(true, person.enabled)


    }

    @Test
    @Order(2)
    fun testDisablePerson() {
        repository.disablePerson(person.id)

        person = repository.findById(person.id).get()

        assertNotNull(person)
        assertNotNull(person.id)
        assertNotNull(person.firstName)
        assertNotNull(person.lastName)
        assertNotNull(person.address)
        assertNotNull(person.gender)

        assertEquals("Abbe", person.firstName)
        assertEquals("Quilleash", person.lastName)
        assertEquals("1 South Avenue", person.address)
        assertEquals("Female", person.gender)
        assertEquals(false, person.enabled)

    }
}