package com.pixabay.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.textChanges
import com.pixabay.R
import com.pixabay.utils.Cons.Companion.ITEM_BUNDLE
import com.pixabay.utils.entities.ImageModel
import com.pixabay.utils.Cons.Companion.MIN_SEARCH_WORD_COUNT
import com.pixabay.utils.Cons.Companion.SEARCH_DO_DELAY
import com.pixabay.utils.models.ErrorIn
import com.pixabay.utils.models.Loading
import com.pixabay.utils.models.Success
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DashboardFragment : Fragment(), ResultsAdapter.OnItemClick {
    @Inject
    lateinit var adapter: ResultsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DashboardViewModel

    private lateinit var compositeDisposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        results.adapter = adapter.also { it.itemClick = this }

        initObservables()
    }

    private fun initObservables() {

        viewModel.result.observe(this, Observer {
            when (it) {
                is Loading -> {
                    progress.visibility = View.VISIBLE
                }
                is ErrorIn -> {
                    progress.visibility = View.GONE
                }
                is Success -> {
                    progress.visibility = View.GONE
                    showResults((it.data as List<ImageModel>))
                }
            }
        })

        //do search when typing on search bar stopped automatically
        compositeDisposable = RxTextView.textChanges(searchBar)
            .skip(MIN_SEARCH_WORD_COUNT)
            .debounce(SEARCH_DO_DELAY, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModel.search(it.toString())
            }
    }

    private fun showResults(hits: List<ImageModel>) {
        adapter.items = hits
    }

    override fun onItemClick(item: ImageModel) {
        val bundle = bundleOf(ITEM_BUNDLE to item)
        findNavController(results).navigate(R.id.action_dashboardFragment_to_detailFragment, bundle)
    }

    override fun onStop() {
        super.onStop()
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}
