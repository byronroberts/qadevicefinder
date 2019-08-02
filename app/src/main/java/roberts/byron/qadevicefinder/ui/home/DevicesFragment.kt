package roberts.byron.qadevicefinder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import roberts.byron.qadevicefinder.R
import roberts.byron.qadevicefinder.domain.Device
import roberts.byron.qadevicefinder.domain.OperatingSystem

class DevicesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        textView.text = "Home fragment"
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val device = Device(
            "1234",
            "someUrl",
            "OnePlus 7",
            OperatingSystem.ANDROID,
            "6.0",
            "Byron Roberts",
            "2019-02-08"
        )
        val database = FirebaseDatabase.getInstance().reference
        database.child("devices").push().setValue(device)
    }
}