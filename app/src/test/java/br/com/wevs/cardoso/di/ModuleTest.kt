package br.com.wevs.cardoso.di

 import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class ModuleTest : AutoCloseKoinTest() {

    @Test
    fun testCoreModule() {
        koinApplication {
            modules(listOf(AppModule,NetworkModule))
        }.checkModules()
    }
}