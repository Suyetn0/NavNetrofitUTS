package id.go.bengkaliskab.mpp.suyetno.tablayoutexample
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ProfileFragment()
            2 -> SettingFragment() // ← tambahkan ini
            else -> HomeFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
