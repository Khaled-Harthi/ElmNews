package com.example.elmnewsapp.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.elmnewsapp.R
import com.example.elmnewsapp.ui.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
@ExperimentalPagingApi
class NewsListFragment : Fragment() {

    private val viewModel : NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsAdapter = NewsAdapter () { selected ->
            viewModel.newsSelected(selected)
            val action = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment()
            view.findNavController().navigate(action)
        }

        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.apply {
            adapter = newsAdapter
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(divider)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.news.collectLatest {
                newsAdapter.submitData(it)
            }
        }


    }
}