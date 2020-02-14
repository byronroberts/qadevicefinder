package roberts.byron.qadevicefinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add_device.*
import roberts.byron.qadevicefinder.R
import roberts.byron.qadevicefinder.domain.Device
import roberts.byron.qadevicefinder.domain.OperatingSystem

class AddDeviceFragment : Fragment() {

    private val args: AddDeviceFragmentArgs by navArgs()
    private val databaseReference = FirebaseDatabase.getInstance().reference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deviceBarcode?.text = args.barcode
        addDeviceFab?.setOnClickListener(doneClickListener)
        databaseReference.addChildEventListener(childEventListener)
    }

    private val doneClickListener = View.OnClickListener {
        val device = Device(
            manufacturer = manufacturerInput?.text?.toString(),
            model = modelInput?.text?.toString(),
            operatingSystem = getOperatingSystem(),
            osVersion = osVersionInput?.text?.toString()
        )

        databaseReference.child("devices").child(args.barcode).setValue(device)
    }

    private fun getOperatingSystem(): OperatingSystem? {
        return when (osRadioGroup.checkedRadioButtonId) {
            R.id.androidRadioButton -> OperatingSystem.ANDROID
            R.id.iosRadioButton -> OperatingSystem.IOS
            else -> OperatingSystem.NONE
        }
    }

    private val childEventListener = object : ChildEventListener {
        override fun onCancelled(databaseError: DatabaseError) {}
        override fun onChildMoved(p0: DataSnapshot, p1: String?) {}
        override fun onChildRemoved(p0: DataSnapshot) {}
        override fun onChildChanged(p0: DataSnapshot, p1: String?) {}

        override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
            val device = dataSnapshot.getValue(Device::class.java)
            view?.let {
                Snackbar.make(
                    it,
                    "${device?.manufacturer} ${device?.model} saved",
                    Snackbar.LENGTH_LONG
                )
            }
            fragmentManager?.popBackStack()
        }
    }

}