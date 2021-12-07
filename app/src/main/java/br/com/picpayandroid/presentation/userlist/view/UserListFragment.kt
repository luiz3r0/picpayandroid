package br.com.picpayandroid.presentation.userlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.picpayandroid.R
import br.com.picpayandroid.databinding.FragmentUserListBinding
import br.com.picpayandroid.presentation.userlist.adapter.UserListAdapter
import br.com.picpayandroid.presentation.userlist.viewmodel.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserListFragment : Fragment() {

    private val userListViewModel: UserListViewModel by viewModel{
        parametersOf(findNavController())
    }
    private lateinit var fragmentUserListBinding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentUserListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        startDataBindingViewModel()
        startDataBindingLiveData()
        startRecyclerView()
        return fragmentUserListBinding.root
    }

    private fun startDataBindingViewModel() {
        fragmentUserListBinding.viewmodel = userListViewModel
    }

    private fun startDataBindingLiveData() {
        fragmentUserListBinding.lifecycleOwner = this
    }

    private fun startRecyclerView() {
        val userListAdapter = UserListAdapter()
        fragmentUserListBinding.recyclerview.setHasFixedSize(true)
        fragmentUserListBinding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        fragmentUserListBinding.recyclerview.adapter = userListAdapter
//        userListViewModel.data.observe(viewLifecycleOwner, {
//            it?.let {
//                userListAdapter.submitList(it)
//            }
//        })
        userListViewModel.dataRoom.observe(viewLifecycleOwner, {
            it?.let {
                userListAdapter.submitList(it)
            }
        })
    }

}