package com.practice.certificationtrainning.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.certificationtrainning.ConcertAdapter
import com.practice.certificationtrainning.R
import com.practice.certificationtrainning.databinding.FragmentFirstBinding
import com.practice.certificationtrainning.ViewModel.ConcertsViewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel : ConcertsViewModel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Instanciar el adapter
        val adapter = ConcertAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.getConcertsList().observe(viewLifecycleOwner, Observer {
            it?.let{
                Log.d("Listado", it.toString())
                adapter.update(it)
            }
        })

        //Método para seleccionar
        adapter.selectedConcert().observe(viewLifecycleOwner, Observer {
            it.let {
                Log.d("Seleccion", it.toString())
            }
            val bundle = Bundle().apply {
                putString("courseId", it.id)
            }

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
