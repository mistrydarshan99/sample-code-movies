package io.petros.movies.data.repository.movie

import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.network.WebService
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature

@ExperimentalCoroutinesApi
class MoviesRepositoryImplSpek : CoroutineSpek({

    val webServiceMock = mockk<WebService>()

    Feature("Movies repository") {
        val testedClass by memoized { MoviesRepositoryImpl(webServiceMock) }
        Scenario("loading movies") {
            When("load movies is triggered") {
                runBlocking { testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) }
            }
            Then("web service triggers load movies") {
                coVerify { webServiceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) }
            }
        }
    }

})
