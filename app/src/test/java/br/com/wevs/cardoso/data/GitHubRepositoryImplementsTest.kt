package br.com.wevs.cardoso.data

import br.com.wevs.cardoso.domain.model.TopJava
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class GitHubRepositoryImplementsTest {

    @MockK
    lateinit var userRespository: GitHubRepositoryImplements

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getListJavaPop() = runBlocking {
        val listTopJava = mockk<TopJava>()
        every { runBlocking { userRespository.getTopListJava(1) } } returns (listTopJava)

        val result = userRespository.getTopListJava(1)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$listTopJava] must be matches on each other!",
            result,
            CoreMatchers.`is`(listTopJava)
        )
    }

    @Test
    fun getListPullRequest() = runBlocking {
        val lisyPullrequest = mockk<TopJava>()
        every { runBlocking { userRespository.getTopListJava(1) } } returns (lisyPullrequest)

        val result = userRespository.getTopListJava(1)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$lisyPullrequest] must be matches on each other!",
            result,
            CoreMatchers.`is`(lisyPullrequest)
        )
    }
}