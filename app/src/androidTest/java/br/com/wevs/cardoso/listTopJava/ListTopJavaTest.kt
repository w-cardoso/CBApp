package br.com.wevs.cardoso.listTopJava

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import br.com.wevs.cardoso.R
import br.com.wevs.cardoso.presentation.activity.MainActivity
import br.com.wevs.cardoso.presentation.fragment.listJava.FragmentListJava
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ListTopJavaTest {

    private fun prepare(func: ListTopJavaRobots.ListTopJavaActivityPrepare.() -> Unit) =
        ListTopJavaRobots.ListTopJavaActivityPrepare().apply { (func()) }

    private fun execute(func: ListTopJavaRobots.ListTopJavaActivityExecute.() -> Unit) =
        ListTopJavaRobots.ListTopJavaActivityExecute().apply { (func()) }

    private fun validate(func: ListTopJavaRobots.ListTopJavaActivityValidate.() -> Unit) =
        ListTopJavaRobots.ListTopJavaActivityValidate().apply { (func()) }

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
                            fragment.requireView(), navController)
                    }
                }
            }
        }
    }

    @Test
    fun shouldDisplayTitle() {
        prepare { openActivity() }
        validate { validateText("List Top Java") }
    }
}