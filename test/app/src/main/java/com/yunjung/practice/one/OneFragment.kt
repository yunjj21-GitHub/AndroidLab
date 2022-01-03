package com.yunjung.practice.one

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import com.yunjung.practice.R
import com.yunjung.practice.databinding.FragmentOneBinding

class OneFragment : Fragment(), OnMapReadyCallback {
    lateinit var binding : FragmentOneBinding
    lateinit var viewModel : OneViewModel

    // 네이버 지도 API 관련
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        private val PERMISSIONS : Array<String> = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    // 뷰가 생성될 때 실행
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false)
        return binding.root
    }

    // 뷰가 완전히 생성되었을 때 실행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(OneViewModel::class.java)
        binding.viewModel = viewModel

        binding.moveFourFgBtn.setOnClickListener {
            findNavController().navigate(R.id.action_oneFragment_to_fourFragment)
        }

        /* 네이버 지도 API 관련 */
        val mapFragment = displayMapOnScreen(childFragmentManager) // 네이버 지도를 화면에 표시
        mapFragment.getMapAsync(this) // 인터페이스 역할을 하는 naverMap 객체를 얻어옴

        // 지도에 현재 위치 추가
        locationSource =
            FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    // 네이버 지도 뷰를 생성
    fun displayMapOnScreen(fm : FragmentManager) : MapFragment{
        return fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
    }

    // naverMap 객체가 준비되면 실행 됨
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        requestPermissions(PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE) // 사용자에게 권한을 요청함

        // 마커를 추가
        createMarkerOnMap(37.5670135, 126.9783740, "어딘가")
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) { // 권한이 거부 되었다면
                naverMap.locationTrackingMode = LocationTrackingMode.None
                return
            }
            else{ // 권한이 인가되었다면
                naverMap.locationTrackingMode = LocationTrackingMode.Follow
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    // 네이버 지도에 마커를 표시
    fun createMarkerOnMap(lat : Double, log : Double, label : String){
        val marker = Marker()
        marker.position = LatLng(lat, log) // 마커의 좌표 설정
        // 마커의 색상 설정
        marker.icon = MarkerIcons.BLACK
        marker.iconTintColor = Color.RED
        marker.map = naverMap
        // 마커의 라벨 설정
        marker.captionText = label
    }
}