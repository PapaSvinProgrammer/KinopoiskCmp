package com.mordva.bottomsheet.packageBottomSheet

import com.mordva.domain.model.PackageType
import com.mordva.domain.model.SearchItem
import com.mordva.domain.model.movie.Movie
import com.mordva.domain.model.totalValue.ReleaseYears
import com.mordva.ui.theme.Icons
import com.mordva.ui.util.ConvertData
import org.jetbrains.compose.resources.DrawableResource

internal fun PackageType.toIcon(isSelected: Boolean): DrawableResource {
    return when (this) {
        PackageType.FAVORITE -> {
            if (isSelected)
                Icons.FolderStarFill
            else
                Icons.FolderStar
        }

        PackageType.WILL_WATCH -> {
            if (isSelected)
                Icons.FolderEyeFill
            else
                Icons.FolderEye
        }
    }
}

internal fun Movie.toSearchItem(): SearchItem {
    return SearchItem(
        id = this.id,
        isMovie = true,
        name = this.name ?: "",
        alternativeName = ConvertData.getAlternativeNameForMovie(
            alternativeName = alternativeName,
            year = year,
            genres = genres.map { it.name },
            start = releaseYears.firstOrNull()?.start,
            end = releaseYears.firstOrNull()?.end
        ),
        year = this.year ?: 0,
        releaseYears = this.releaseYears.firstOrNull() ?: ReleaseYears(null, null),
        poster = this.poster?.url ?: "",
        rating = this.rating?.kp ?: 0f
    )
}