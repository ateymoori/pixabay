package com.pixabay

import android.os.Handler
import android.os.Looper
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pixabay.repo.repo.MainRepo
import com.pixabay.ui.dashboard.DashboardViewModel
import com.pixabay.utils.models.Response
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
open class TestViewModel {

    @MockK(relaxed = true)
    lateinit var mainRepo: MainRepo

    lateinit var dashboardViewModel: DashboardViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dashboardViewModel = DashboardViewModel(mainRepo)
    }


    @Test
    fun testSearch() {

        dashboardViewModel.search("cat")

        //Handler(Looper.getMainLooper()).post {
//            dashboardViewModel.result.observeForever {
//                assertEquals((it is Response), false)
//            }
       // }

//        runBlocking {
//            dashboardViewModel.result.observeForever {
//                assertEquals((it is Response), false)
//            }
//        }
        GlobalScope.launch(Dispatchers.Main) {
            dashboardViewModel.result.observeForever {
                assertEquals((it is Response), false)
            }
        }

//        CoroutineScope(Dispatchers.IO).launch {
//            dashboardViewModel.result.observeForever {
//                assertEquals((it is Response), false)
//            }
//        }

    }
}