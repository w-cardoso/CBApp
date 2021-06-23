package br.com.wevs.cardoso.listTopJava

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ListTopJavaTest {

    private fun prepare(func: ListTopJavaRobots.ListTopJavaActivityPrepare.() -> Unit) =
        ListTopJavaRobots.ListTopJavaActivityPrepare().apply { (func()) }

    private fun execute(func: ListTopJavaRobots.ListTopJavaActivityExecute.() -> Unit) =
        ListTopJavaRobots.ListTopJavaActivityExecute().apply { (func()) }

    private fun validate(func: ListTopJavaRobots.ListTopJavaActivityValidate.() -> Unit) =
        ListTopJavaRobots.ListTopJavaActivityValidate().apply { (func()) }


}