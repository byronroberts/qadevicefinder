package roberts.byron.qadevicefinder.ui.devices

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_devices.*
import roberts.byron.qadevicefinder.R
import roberts.byron.qadevicefinder.domain.Device


class DevicesFragment : Fragment() {

    private val databaseReference = FirebaseDatabase.getInstance().reference
    private val devicesAdapter = DevicesRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deviceReference = databaseReference.child("devices")
        deviceReference.addValueEventListener(valueEventListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_devices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deviceRecyclerView.layoutManager = LinearLayoutManager(context)
        deviceRecyclerView.addItemDecoration(RecyclerViewDividerDecoration(R.dimen.recycler_item_spacing))
        deviceRecyclerView.adapter = devicesAdapter
    }

    private val valueEventListener = object : ValueEventListener {
        override fun onCancelled(databaseError: DatabaseError) {}

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val devices = mutableListOf<Device?>()
            dataSnapshot.children.forEach {
                devices.add(it.getValue(Device::class.java))
            }
            devicesAdapter.devices = devices
            devicesAdapter.notifyDataSetChanged()
        }
    }

    inner class RecyclerViewDividerDecoration(itemOffsetId: Int) : RecyclerView.ItemDecoration() {

        private val dividerSize = deviceRecyclerView.context.resources.getDimensionPixelSize(itemOffsetId)

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                    state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(0, 0, 0, dividerSize)
        }
    }

}