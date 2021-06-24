package br.com.wevs.cardoso.listPullRequest

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import br.com.wevs.cardoso.RecyclerViewMatchers
import br.com.wevs.cardoso.presentation.activity.MainActivity

class ListPullRequestRobots {
    class ListPullRequestActivityPrepare {
        fun openActivity() = launchActivity<MainActivity>()
    }

    class ListPullRequestActivityValidate {
        fun validateText(text: String) {
            Espresso.onView(ViewMatchers.withText(text))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun validateItemRecyclerView(
            id: Int,
            position: Int,
            text: String
        ) {
            RecyclerViewMatchers.checkRecyclerViewItem(id, position, ViewMatchers.withText(text))
        }
    }
}