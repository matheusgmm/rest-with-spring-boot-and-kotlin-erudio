package br.com.erudio.integrationtests.controller.withxml

import br.com.erudio.integrationtests.ConfigsTest
import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest
import br.com.erudio.integrationtests.vo.AccountCredentialsVO
import br.com.erudio.integrationtests.vo.TokenVO
import io.restassured.RestAssured
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerXmlTest : AbstractIntegrationTest() {


    private lateinit var tokenVO: TokenVO

    @BeforeAll
    fun setupTests() {
        tokenVO = TokenVO()
    }

    @Test
    @Order(0)
    fun testLogin() {
        val user = AccountCredentialsVO(
            username = "matheus",
            password = "root"
        )

        tokenVO = RestAssured.given().basePath("/auth/signin")
                .port(ConfigsTest.SERVER_PORT)
                .contentType(ConfigsTest.CONTENT_TYPE_XML)
                .body(user)
            .`when`()
                .post()
                    .then()
                .statusCode(200)
                    .extract()
                    .body()
                .`as`(TokenVO::class.java)

        assertNotNull(tokenVO.accessToken)
        assertNotNull(tokenVO.refreshToken)
    }

    @Test
    @Order(1)
    fun testRefreshToken() {

        tokenVO = RestAssured.given().basePath("/auth/refresh")
                .port(ConfigsTest.SERVER_PORT)
                .contentType(ConfigsTest.CONTENT_TYPE_XML)
                .pathParam("username", tokenVO.username)
                .header(
                    ConfigsTest.HEADER_PARAM_AUTHORIZATION,
                    "Bearer ${tokenVO.refreshToken}"
                )
            .`when`()
                .put("{username}")
                    .then()
                .statusCode(200)
                    .extract()
                    .body()
                .`as`(TokenVO::class.java)

        assertNotNull(tokenVO.accessToken)
        assertNotNull(tokenVO.refreshToken)
    }
}