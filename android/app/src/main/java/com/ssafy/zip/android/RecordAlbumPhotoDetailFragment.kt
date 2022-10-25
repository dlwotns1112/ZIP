package com.ssafy.zip.android

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.zip.android.adapter.PhotoSlideAdapter
import com.ssafy.zip.android.adapter.PhotoThumbnailAdapter
import com.ssafy.zip.android.data.Photo
import kotlin.properties.Delegates


class RecordAlbumPhotoDetailFragment : Fragment() {
    var imageList : ArrayList<Photo> = ArrayList() // 후에는 Uri로 바뀔듯
    private var position by Delegates.notNull<Int>() // 이미지 현재 위치
    private lateinit var viewPager : ViewPager2
    private lateinit var photoSlideAdapter: PhotoSlideAdapter
    private lateinit var photoThumbnailAdapter: PhotoThumbnailAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        var view : View = inflater.inflate(R.layout.fragment_record_album_photo_detail, container, false)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record_album_photo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.photo_appbar)

        // 앨범명으로 appbar title 지정
        toolbar.title = arguments?.getString("albumTitle")

        imageList = arguments?.getParcelableArrayList<Photo>("photoList") as ArrayList<Photo>
        photoSlideAdapter = PhotoSlideAdapter(imageList)
        photoThumbnailAdapter = PhotoThumbnailAdapter(imageList)

        position = arguments?.getInt("photoImage")?.let { getPosition(it) }!!

        viewPager = view.findViewById(R.id.photo_viewpager)
        viewPager.adapter = photoSlideAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향 설정
        viewPager.offscreenPageLimit = 1 // 관리할 페이지 수
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // page 바뀔 때마다 호출
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                recyclerView.scrollToPosition(position)
                println("Page ${position+1}")
            }
        })
//        viewPager.setCurrentItem(position, false)

        recyclerView = view.findViewById(R.id.photo_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = photoThumbnailAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // list에서의 현재 이미지의 위치 업데이트
        arguments?.getInt("photoImage")?.let { setPosition(it, false) }

//        val imageView : ImageView = view.findViewById(R.id.photo_image)
//
//        arguments?.getInt("photoImage")?.let { imageView.setImageResource(it) }


        // 뒤로가기
        val backBtn : View = view.findViewById(R.id.back_btn)
        val forwardBtn : View = view.findViewById(R.id.forward_btn)

        backBtn.setOnClickListener{
            if(position > 0){
                position--
                // 이미지 보여주기
                setPosition(imageList[position].image, true)
//                viewPager.setCurrentItem(position, true)
            }
        }

        forwardBtn.setOnClickListener{
            if(position < imageList.size-1){
                position++
                // 이미지 보여주기
                setPosition(imageList[position].image, true)
//                viewPager.setCurrentItem(position, true)
            }
        }
    }

    inner class roomListAdapterToList {
        fun getPhoto(photo: Photo) {
            setPosition(photo.image, false)
        }
    }

    private fun setPosition(image: Int, smoothScroller : Boolean) {
        val position = getPosition(image)
        viewPager.setCurrentItem(position, smoothScroller)
        recyclerView.scrollToPosition(position)
    }

    private fun getPosition(image: Int): Int {
        return imageList.indexOfFirst{
            it.image == image
        }
    }
}