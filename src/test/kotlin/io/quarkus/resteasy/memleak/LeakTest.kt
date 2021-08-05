package io.quarkus.resteasy.memleak

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

@QuarkusTest
class LeakTest {

    @Test
    fun leakTest() {
        runBlocking {
            (1..10)
                    .map {
                        GlobalScope.async {
                            RestAssured.given()
                                    .`when`()
                                    .get("/v1/getthings")
                                    .then()
                                    .statusCode(200)
                        }
                    }
                    .joinAll()
        }
    }
}
