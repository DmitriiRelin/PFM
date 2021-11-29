package com.example.pfm.ui.deatil

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pfm.App
import com.example.pfm.R
import com.example.pfm.utils.loadImage
import com.example.pfm.databinding.FragmentDetailBinding
import com.example.pfm.domain.entites.People
import com.example.pfm.utils.EventObserver
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: DetailViewModelFactory

    val viewModel: DetailVieModel by viewModels { viewModelFactory }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val arguments: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail, container, false).apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val argsPeople = arguments.people
        argsPeople.let { people ->
            if (people != null) {
                viewModel.start(people)
//                setAllFields(people)
            }
        }

        viewModel.peopleLiveData.observe(viewLifecycleOwner) {
            setAllFields(it)
            if (it.isInFavorite) {
                binding.addToFavorite.setImageResource(R.drawable.ic_baseline_star_24)
                editable()
                setEditFields(it)
            } else {
                binding.addToFavorite.setImageResource(R.drawable.ic_baseline_star_border_24)
                notEditable()
            }

        }

       /* viewModel.editFailureEvent.observe(viewLifecycleOwner, EventObserver{
            Snackbar.make(binding.root, "вы не ввели: $it", Snackbar.LENGTH_LONG).show()
        })*/

        binding.addToFavorite.setOnClickListener {
            viewModel.changeFavoriteStatus()
        }

        binding.save.setOnClickListener {
            viewModel.savePeopleChanges()
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    private fun editable() {
        binding.editable.visibility = View.VISIBLE
        binding.notEditable.visibility = View.GONE

    }

    private fun notEditable() {
        binding.editable.visibility = View.GONE
        binding.notEditable.visibility = View.VISIBLE
    }

    private fun setEditFields(people: People) {
        binding.lastNameEdit.setText(people.lastName)
        binding.firstNameEdit.setText(people.firstName)
        binding.emailEdit.setText(people.email)
    }


    private fun setAllFields(people: People) {
        view?.let { loadImage(it.context, people.avatar, binding.avatar) }
        binding.firstName.text = people.firstName
        binding.lastName.text = people.lastName
        binding.email.text = people.email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}