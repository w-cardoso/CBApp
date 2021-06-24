package br.com.wevs.cardoso.listPullRequest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import br.com.wevs.cardoso.R
import br.com.wevs.cardoso.presentation.activity.MainActivity
import br.com.wevs.cardoso.presentation.fragment.listJava.FragmentListJava
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ListPullRequestTest {

    private fun prepare(func: ListPullRequestRobots.ListPullRequestActivityPrepare.() -> Unit) =
        ListPullRequestRobots.ListPullRequestActivityPrepare().apply { (func()) }

    private fun validate(func: ListPullRequestRobots.ListPullRequestActivityValidate.() -> Unit) =
        ListPullRequestRobots.ListPullRequestActivityValidate().apply { (func()) }

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)

        val scenario = launchFragmentInContainer {
            FragmentListJava().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { lifecycle ->
                    if (lifecycle != null) {
                        Navigation.setViewNavController(
                            fragment.requireView(), navController
                        )
                    }
                }
            }
        }
    }
}