package com.mordva.movie.presentation.movie

import androidx.lifecycle.ViewModel
import com.mordva.bottomsheet.packageBottomSheet.PackageItemAction
import com.mordva.domain.model.PackageType
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.person.PersonMovie
import com.mordva.domain.repository.BlockedRepository
import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.repository.FavoritePackageRepository
import com.mordva.domain.repository.ImageRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.RatedMovieRepository
import com.mordva.domain.repository.ViewedRepository
import com.mordva.domain.repository.WillWatchPackageRepository
import com.mordva.movie.domain.comment.GetCommentByDate
import com.mordva.movie.domain.filterCollection
import com.mordva.movie.domain.filterPersonsLikeActors
import com.mordva.movie.domain.filterPersonsLikeSupport
import com.mordva.movie.domain.filterPersonsLikeVoiceActors
import com.mordva.movie.domain.handle.HandleBlockedAction
import com.mordva.movie.domain.handle.HandleFavoritePackageAction
import com.mordva.movie.domain.handle.HandleRatedMovieAction
import com.mordva.movie.domain.handle.HandleViewedAction
import com.mordva.movie.domain.handle.HandleWillWatchAction
import com.mordva.movie.domain.model.CheckedParams
import com.mordva.movie.domain.model.CommentParams
import com.mordva.movie.domain.model.PackageItemParams
import com.mordva.movie.domain.model.RatedMovieActionParams
import com.mordva.movie.presentation.movie.widget.MovieState
import com.mordva.movie.presentation.movie.widget.MovieUIState
import com.mordva.movie.presentation.movie.widget.scoreBottomSheet.RatedMovieState
import com.mordva.movie.presentation.movie.widget.scoreBottomSheet.ScoreSheetAction
import com.mordva.movie.utils.body
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import com.mordva.util.multiRequest
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

internal class MovieViewModel(
    private val movieId: Int,
    private val getCommentByDate: GetCommentByDate,
    private val ratedMovieRepository: RatedMovieRepository,
    private val willWatchPackageRepository: WillWatchPackageRepository,
    private val handleWillWatchAction: HandleWillWatchAction,
    private val handleRatedMovieAction: HandleRatedMovieAction,
    private val handleFavoritePackageAction: HandleFavoritePackageAction,
    private val handleViewedAction: HandleViewedAction,
    private val handleBlockedAction: HandleBlockedAction,
    private val favoritePackageRepository: FavoritePackageRepository,
    private val viewedRepository: ViewedRepository,
    private val blockedRepository: BlockedRepository,
    private val movieRepository: MovieRepository,
    private val imageRepository: ImageRepository,
    private val collectionRepository: CollectionRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovie()
        getImages()
        getComments()
        observeMovieState()
    }

    fun updatePackageVisible(visible: Boolean) {
        _uiState.update {
            it.copy(packageSheetVisible = visible)
        }
    }

    fun updateMoreSheetVisible(visible: Boolean) {
        _uiState.update {
            it.copy(moreSheetVisible = visible)
        }
    }

    fun updateScoreSheetVisible(visible: Boolean) {
        _uiState.update {
            it.copy(scoreSheetVisible = visible)
        }
    }

    fun updateSelectedFact(text: String) {
        _uiState.update {
            it.copy(selectedFact = text)
        }
    }

    fun updateCurrentRatingMovie(rating: Int) {
        _uiState.update { it.copy(currentMovieRating = rating) }
    }

    fun handleScoreSheetHandle(action: ScoreSheetAction) = launchWithoutOld(RATED_MOVIE_JOB) {
        val params = RatedMovieActionParams(
            scoreSheetAction = action,
            currentScore = uiState.value.currentMovieRating,
            movie = uiState.value.movieState.body(),
        )

        handleRatedMovieAction.execute(params)
    }

    fun handleViewedAction() = launchWithoutOld(VIEWED_JOB) {
        val params = CheckedParams(
            isChecked = uiState.value.isViewed,
            movieId = uiState.value.movieState.body().id
        )

        handleViewedAction.execute(params)
    }

    fun handleBlockedAction() = launchWithoutOld(BLOCKED_JOB) {
        val params = CheckedParams(
            isChecked = uiState.value.isBlocked,
            movieId = uiState.value.movieState.body().id
        )

        handleBlockedAction.execute(params)
    }

    fun handleWillWatchAction() = launchWithoutOld(WILL_WATCH_JOB) {
        val params = CheckedParams(
            isChecked = uiState.value.isWillWatch,
            movieId = uiState.value.movieState.body().id
        )

        handleWillWatchAction.execute(params)
    }

    fun getRatedMovies(rating: Int) = launchWithoutOld(RATED_MOVIE_JOB) {
        _uiState.update { it.copy(ratedMoviesState = RatedMovieState.Loading) }

        ratedMovieRepository.getAllByRating(rating).collect { movies ->
            _uiState.update {
                it.copy(ratedMoviesState = RatedMovieState.Success(movies))
            }
        }
    }

    fun handlePackageAction(action: PackageItemAction) = launchWithoutOld(FAVORITE_PACKAGE_JOB) {
        val params = PackageItemParams(
            action = action,
            movieId = uiState.value.movieState.body().id
        )

        handleFavoritePackageAction.execute(params)
    }

    private fun observeMovieState() = launchWithoutOld(OBSERVE) {
        uiState.map { it.movieState }
            .distinctUntilChanged()
            .collectLatest { state ->
                state.body().let {
                    save(it)
                    isRatedMovie()
                    isWillWatchPackage()
                    isBlocked()
                    isViewed()
                    getCollections(it.lists)
                    getInfoForPackages()
                    this.cancel()
                }
            }
    }

    private fun save(movie: Movie) = launchWithoutOld {
//        movieLocalService.insert(movie)
    }

    private fun isRatedMovie() = launchWithoutOld(IS_RATED_MOVIE_JOB) {
        ratedMovieRepository.isStock(movieId).collect { ratedMovie ->
            _uiState.update {
                it.copy(isRatedMovieState = ratedMovie)
            }
        }
    }

    private fun getComments() = launchWithoutOld(GET_COMMENTS_JOB) {
        val params = CommentParams(
            movieId = movieId,
            sort = -1
        )

        getCommentByDate.execute(params).onSuccess { comments ->
            _uiState.update {
                it.copy(comments = comments)
            }
        }
    }

    private fun getMovie() = launchWithoutOld(GET_MOVIE_JOB) {
        val res = movieRepository.getMovieById(movieId)

        res.onSuccess { movie ->
            val state = MovieState.Success(movie)

            _uiState.update {
                it.copy(movieState = state)
            }

            filterActors(movie.persons)
            movieRepository.save(movie)
        }
    }

    private fun getImages() = launchWithoutOld(GET_IMAGES_JOB) {
        val res = imageRepository.getMovieImages(movieId)

        res.onSuccess { images ->
            _uiState.update { it.copy(images = images) }
        }
    }

    private fun getCollections(list: List<String>) = launchWithoutOld(GET_COLLECTIONS) {
        if (uiState.value.collections.isNotEmpty()) {
            return@launchWithoutOld
        }

        val temp = multiRequest(list) {
            collectionRepository.getCollectionBySlug(it)
        }

        if (temp.isNotEmpty()) {
            _uiState.update {
                it.copy(collections = temp.filterCollection())
            }
        }
    }

    private fun isWillWatchPackage() = launchWithoutOld(IS_WILL_WATCH_JOB) {
        willWatchPackageRepository.isStock(movieId).collect { result ->
            val set = uiState.value.selectedPackage.toMutableSet()

            _uiState.update {
                it.copy(isWillWatch = result != null)
            }

            if (result != null) {
                set.add(PackageType.WILL_WATCH)
            } else {
                set.remove(PackageType.WILL_WATCH)
            }

            _uiState.update { it.copy(selectedPackage = set) }
        }
    }

    private fun isBlocked() = launchWithoutOld(IS_BLOCKED_JPB) {
        blockedRepository.isStock(movieId).collect { moviePackage ->
            _uiState.update {
                it.copy(isBlocked = moviePackage != null)
            }
        }
    }

    private fun isViewed() = launchWithoutOld(IS_VIEWED_JOB) {
        viewedRepository.isStock(movieId).collect { moviePackage ->
            _uiState.update {
                it.copy(isViewed = moviePackage != null)
            }
        }
    }

    private fun getInfoForPackages() {
        isFavoritePackage()
        getCountFavoritePackage()
        getCountWillWatchPackage()
    }

    private fun getCountFavoritePackage() = launchWithoutOld(COUNT_FAVORITE_JOB) {
        favoritePackageRepository.count().collect { count ->
            val map = uiState.value.packageSize.toMutableMap()
            map[PackageType.FAVORITE] = count
            _uiState.update { it.copy(packageSize = map) }
        }
    }

    private fun getCountWillWatchPackage() = launchWithoutOld(COUNT_WILL_WATCH_JOB) {
        willWatchPackageRepository.count().collect { count ->
            val map = uiState.value.packageSize.toMutableMap()
            map[PackageType.WILL_WATCH] = count
            _uiState.update { it.copy(packageSize = map) }
        }
    }

    private fun isFavoritePackage() = launchWithoutOld(IS_FAVORITE_PACKAGE_JOB) {
        favoritePackageRepository.isStock(movieId).collect { moviePackage ->
            val set = uiState.value.selectedPackage.toMutableSet()

            if (moviePackage != null) {
                set.add(PackageType.FAVORITE)
            } else {
                set.remove(PackageType.FAVORITE)
            }

            _uiState.update { it.copy(selectedPackage = set) }
        }
    }

    private fun filterActors(list: List<PersonMovie>) = launchWithoutOld(FILTER_ACTORS_JOB) {
        _uiState.update {
            it.copy(
                actors = list.filterPersonsLikeActors(),
                voiceActors = list.filterPersonsLikeVoiceActors(),
                supportPersonal = list.filterPersonsLikeSupport(),
            )
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val VIEWED_JOB = "viewed_action"
        const val IS_VIEWED_JOB = "is_viewed_movie"
        const val BLOCKED_JOB = "blocked_action_job"
        const val IS_BLOCKED_JPB = "is_blocked_movie"
        const val COUNT_WILL_WATCH_JOB = "count_will_watch_job"
        const val COUNT_FAVORITE_JOB = "count_favorite_package"
        const val IS_FAVORITE_PACKAGE_JOB = "is_favorite_package"
        const val FAVORITE_PACKAGE_JOB = "action_favorite_package"
        const val WILL_WATCH_JOB = "action_will_watch_job"
        const val IS_WILL_WATCH_JOB = "is_will_watch_package"
        const val RATED_MOVIE_JOB = "action_for_rated_movie"
        const val IS_RATED_MOVIE_JOB = "is_rated_movie"
        const val GET_COMMENTS_JOB = "get_comments"
        const val GET_MOVIE_JOB = "get_movie"
        const val GET_IMAGES_JOB = "get_images"
        const val GET_COLLECTIONS = "get_collections"
        const val FILTER_ACTORS_JOB = "filter_actors"
        const val OBSERVE = "observe_ui_state"
    }
}
