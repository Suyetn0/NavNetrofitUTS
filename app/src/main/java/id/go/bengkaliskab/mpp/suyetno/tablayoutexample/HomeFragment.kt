package id.go.bengkaliskab.mpp.suyetno.tablayoutexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.go.bengkaliskab.mpp.suyetno.tablayoutexample.databinding.FragmentHomeBinding
import androidx.recyclerview.widget.LinearLayoutManager
import id.go.bengkaliskab.mpp.suyetno.tablayoutexample.adapter.PersonAdapter
import id.go.bengkaliskab.mpp.suyetno.tablayoutexample.model.PersonResponse
import id.go.bengkaliskab.mpp.suyetno.tablayoutexample.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPersons()
    }
    private fun getPersons() {

        ApiConfig.getApiService().getPersons(
            10,
            "id_ID",
            "male"
        ).enqueue(object : Callback<PersonResponse> {

            override fun onResponse(
                call: Call<PersonResponse>,
                response: Response<PersonResponse>
            ) {

                if (response.isSuccessful) {

                    val personList = response.body()?.data

                    if (personList != null) {

                        val adapter = PersonAdapter(personList)

                        binding.rvPerson.layoutManager =
                            LinearLayoutManager(requireContext())

                        binding.rvPerson.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {

                t.printStackTrace()
            }
        })
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}