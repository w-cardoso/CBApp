package br.com.wevs.cardoso.listTopJava

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import br.com.wevs.cardoso.RecyclerViewMatchers.checkRecyclerViewItem
import br.com.wevs.cardoso.presentation.activity.MainActivity


class ListTopJavaRobots {
    class ListTopJavaActivityPrepare {
        fun openActivity() = launchActivity<MainActivity>()
    }

    class ListTopJavaActivityExecute

    class ListTopJavaActivityValidate {
        fun validateText(text: String) {
            Espresso.onView(ViewMatchers.withText(text))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun validateItemRecyclerView(
            id: Int,
            position: Int,
            text: String
        ) {
            checkRecyclerViewItem(id, position, ViewMatchers.withText(text))
        }
    }
}
