package com.paging.ex1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paging.R
import com.paging.ex1.data.MyApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PassengersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class PassengersFragment : Fragment() {
    private lateinit var viewModel: PassengersViewModel
    private lateinit var rv: RecyclerView
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passengers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = PassengersViewModelFactory(MyApi())
        viewModel = ViewModelProvider(this, factory).get(PassengersViewModel::class.java)
        val passengersAdapter = PassengersAdapter()
        rv = view.findViewById(R.id.recycler_view)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = passengersAdapter.withLoadStateHeaderAndFooter(
            header = PassengersLoadStateAdapter { passengersAdapter.retry() },
            footer = PassengersLoadStateAdapter { passengersAdapter.retry() }
        )
        lifecycleScope.launch {
            viewModel.passengers.collectLatest { pagedData ->
                passengersAdapter.submitData(pagedData)
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PassengersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PassengersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}