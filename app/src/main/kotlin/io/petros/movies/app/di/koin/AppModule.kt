package io.petros.movies.app.di.koin

import io.petros.movies.movies.di.koin.moviesModule
import io.petros.movies.splash.di.koin.splashModule

private val featureModules = splashModule + moviesModule

val appModule = featureModules
