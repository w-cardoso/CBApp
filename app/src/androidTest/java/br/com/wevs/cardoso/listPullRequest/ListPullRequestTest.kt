package br.com.wevs.cardoso.listPullRequest

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ListPullRequestTest {
    private fun prepare(func: ListPullRequestRobots.ListPullRequestActivityPrepare.() -> Unit) =
        ListPullRequestRobots.ListPullRequestActivityPrepare().apply { (func()) }

    private fun execute(func: ListPullRequestRobots.ListPullRequestActivityExecute.() -> Unit) =
        ListPullRequestRobots.ListPullRequestActivityExecute().apply { (func()) }

    private fun validate(func: ListPullRequestRobots.ListPullRequestActivityValidate.() -> Unit) =
        ListPullRequestRobots.ListPullRequestActivityValidate().apply { (func()) }
}