package io.petros.movies.presentation.feature.common.picker

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.whiteelephant.monthpicker.MonthPickerDialog
import io.petros.movies.R
import java.util.*

class MovieYearPickerFragment : DialogFragment(), MonthPickerDialog.OnDateSetListener {

    companion object {

        const val TAG = "MovieYearPickerFragment"

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        return MonthPickerDialog.Builder(context, this, year, 0)
            .setActivatedYear(year)
            .setMaxYear(year)
            .setTitle(context?.getString(R.string.dialog_picker_movie_year))
            .showYearOnly()
            .build()
    }

    override fun onDateSet(selectedMonth: Int, selectedYear: Int) {
        (activity as? MovieYearPickerFragmentCallback)?.onYearPicked(selectedYear)
    }

}