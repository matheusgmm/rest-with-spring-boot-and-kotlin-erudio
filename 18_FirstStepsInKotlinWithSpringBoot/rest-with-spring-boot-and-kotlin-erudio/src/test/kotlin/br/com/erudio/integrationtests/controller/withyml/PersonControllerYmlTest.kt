package br.com.erudio.integrationtests.controller.withyml

import br.com.erudio.integrationtests.ConfigsTest
import br.com.erudio.integrationtests.controller.withyml.mapper.YMLMapper
import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest
import br.com.erudio.integrationtests.vo.AccountCredentialsVO
import br.com.erudio.integrationtests.vo.PersonVO
import br.com.erudio.integrationtests.vo.TokenVO
import br.com.erudio.model.Person
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.EncoderConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonControllerYmlTest : AbstractIntegrationTest() {

    private lateinit var specification: RequestSpecification
    private lateinit var objectMapper: YMLMapper;
    private lateinit var person: PersonVO

    @BeforeAll
    fun setupTests() {
        objectMapper = YMLMapper()
        person = PersonVO()
    }

    @Test
    @Order(0)
    fun testLogin() {
        val user = AccountCredentialsVO(
            username = "matheus",
            password = "root"
        )

        val token = RestAssured.given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(ConfigsTest.CONTENT_TYPE_YAML, ContentType.TEXT)
                    )
            )
            .basePath("/auth/signin")
                .port(ConfigsTest.SERVER_PORT)
                .contentType(ConfigsTest.CONTENT_TYPE_YAML)
                .body(user, objectMapper)
            .`when`()
                .post()
                    .then()
                .statusCode(200)
                    .extract()
                    .body()
                .`as`(TokenVO::class.java, objectMapper)
                    .accessToken

        specification = RequestSpecBuilder()
            .addHeader(ConfigsTest.HEADER_PARAM_AUTHORIZATION, "Bearer $token")
            .setBasePath("/api/person/v1")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()
    }


    @Test
    @Order(1)
    fun testCreate() {
        mockPerson()

        val item = given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(ConfigsTest.CONTENT_TYPE_YAML, ContentType.TEXT)
                    )
            )
                .spec(specification)
                .contentType(ConfigsTest.CONTENT_TYPE_YAML)
                .body(person, objectMapper)
            .`when`()
                .post ()
                    .then()
                .statusCode(200)
                    .extract()
                    .body()
                .`as`(PersonVO::class.java, objectMapper)

        person = item


        assertNotNull(item.id)
        assertTrue(item.id > 0)
        assertNotNull(item.firstName)
        assertNotNull(item.lastName)
        assertNotNull(item.address)
        assertNotNull(item.gender)

        assertEquals("Richard", item.firstName)
        assertEquals("Stallman", item.lastName)
        assertEquals("New York City, New York, US", item.address)
        assertEquals("Male", item.gender)


    }

    @Test
    @Order(2)
    fun testUpdate() {
        person.lastName = "Matthew Stallman"

        val item = given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(ConfigsTest.CONTENT_TYPE_YAML, ContentType.TEXT)
                    )
            )
                .spec(specification)
                .contentType(ConfigsTest.CONTENT_TYPE_YAML)
                .body(person, objectMapper)
            .`when`()
                .put ()
                    .then()
                .statusCode(200)
                    .extract()
                    .body()
                .`as`(PersonVO::class.java, objectMapper)


        person = item


        assertNotNull(item.id)
        assertNotNull(item.firstName)
        assertNotNull(item.lastName)
        assertNotNull(item.address)
        assertNotNull(item.gender)

        assertEquals(person.id, item.id)
        assertEquals("Richard", item.firstName)
        assertEquals("Matthew Stallman", item.lastName)
        assertEquals("New York City, New York, US", item.address)
        assertEquals("Male", item.gender)
    }


    @Test
    @Order(3)
    fun testFindById() {
        val item = given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(ConfigsTest.CONTENT_TYPE_YAML, ContentType.TEXT)
                    )
            )
                .spec(specification)
                .contentType(ConfigsTest.CONTENT_TYPE_YAML)
                .pathParam("id", person.id)
            .`when`()
                .get("{id}")
                    .then()
                .statusCode(200)
                    .extract()
                    .body()
                .`as`(PersonVO::class.java, objectMapper)

        person = item


        assertNotNull(item.id)
        assertNotNull(item.firstName)
        assertNotNull(item.lastName)
        assertNotNull(item.address)
        assertNotNull(item.gender)

        assertEquals(person.id, item.id)
        assertEquals("Richard", item.firstName)
        assertEquals("Matthew Stallman", item.lastName)
        assertEquals("New York City, New York, US", item.address)
        assertEquals("Male", item.gender)
    }

    @Test
    @Order(4)
    fun testDelete() {
        given()
            .spec(specification)
            .pathParam("id", person.id)
            .`when`()
                .delete("{id}")
                    .then()
                .statusCode(204)
    }


    @Test
    @Order(5)
    fun testFindAll() {
        val people = given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(ConfigsTest.CONTENT_TYPE_YAML, ContentType.TEXT)
                    )
            )
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_YAML)
            .`when`()
            .get()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .`as`(Array<PersonVO>::class.java, objectMapper)


        val item1 = people[0]


        assertNotNull(item1.id)
        assertNotNull(item1.firstName)
        assertNotNull(item1.lastName)
        assertNotNull(item1.address)
        assertNotNull(item1.gender)

        assertEquals("Matheus", item1.firstName)
        assertEquals("Muccio", item1.lastName)
        assertEquals("Tupã - São Paulo - Brasil", item1.address)
        assertEquals("Male", item1.gender)


        val item2 = people[1]

        assertNotNull(item2.id)
        assertNotNull(item2.firstName)
        assertNotNull(item2.lastName)
        assertNotNull(item2.address)
        assertNotNull(item2.gender)

        assertEquals("teste", item2.firstName)
        assertEquals("teste", item2.lastName)
        assertEquals("teste", item2.address)
        assertEquals("teste", item2.gender)
    }


    @Test
    @Order(6)
    fun testFindAllWithoutToken() {

        val specificationWithoutToken: RequestSpecification = RequestSpecBuilder()
            .setBasePath("/api/person/v1")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()

        given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig()
                            .encodeContentTypeAs(ConfigsTest.CONTENT_TYPE_YAML, ContentType.TEXT)
                    )
            )
            .spec(specificationWithoutToken)
            .contentType(ConfigsTest.CONTENT_TYPE_YAML)
            .`when`()
            .get()
            .then()
            .statusCode(403)
            .extract()
            .body()
            .`as`(PersonVO::class.java, objectMapper)
    }



    private fun mockPerson() {
        person.firstName = "Richard"
        person.lastName = "Stallman"
        person.address = "New York City, New York, US"
        person.gender =  "Male"
    }
}