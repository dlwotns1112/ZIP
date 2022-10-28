package com.ssafy.zip.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.zip.android.adapter.HomeAdapter
import com.ssafy.zip.android.data.User
import com.ssafy.zip.android.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeList: ArrayList<User>
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.mission1Btn.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToRecordFragment()
            binding.root.findNavController().navigate(action)
        }

        binding.mission2Btn.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToRecordFragment()
            binding.root.findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeRecyclerView.setHasFixedSize(true)

        homeList = ArrayList()
        addDataToList()

        homeAdapter = HomeAdapter(homeList, childFragmentManager)
        binding.homeRecyclerView.adapter = homeAdapter

        val cnt = when(homeAdapter.itemCount){
            in 1..4 -> homeAdapter.itemCount
            in 5..6 -> 3
            else -> 4
        }
        binding.homeRecyclerView.layoutManager = GridLayoutManager(activity, cnt)
    }

    private fun addDataToList() {
//        homeList.add(Member(1,"류현수", "행복한 우리 가조쿠", "귀요미 막둥이 현수", R.drawable.member1))
//        homeList.add(Member(2,"김민균", "행복한 우리 가조쿠", "귀요미 첫째 민균", R.drawable.member2))
//        homeList.add(Member(3,"이승연", "행복한 우리 가조쿠", "귀요미 둘째 승연", R.drawable.member3))
//        homeList.add(Member(4,"이보나", "행복한 우리 가조쿠", "귀요미 셋째 보나", R.drawable.member4))
//        homeList.add(Member(5, R.drawable.member4))
    }

}