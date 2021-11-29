package com.example.pfm.ui.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pfm.App
import com.example.pfm.databinding.FragmentFavoritesBinding
import com.example.pfm.ui.favorites.rv.FavoritesAdapter
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: FavoritesViewModelFactory

    val viewModel: FavoritesViewModel by viewModels { viewModelFactory }


    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesAdapter = FavoritesAdapter(
        onItemClickListenerFavorites = {
            val action = FavoritesFragmentDirections.actionNavigationFavoritesToDetailFragment(it)
            findNavController().navigate(action)
        },
        onItemClickDelete = {
            viewModel.delete(it)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getList()
        setAdapter()
        viewModel.listPeopleLiveData.observe(viewLifecycleOwner) {
            favoritesAdapter.submitList(it)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    private fun setAdapter() {
        binding.recyclerFavorites.layoutManager = LinearLayoutManager(context)
        binding.recyclerFavorites.adapter = favoritesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}